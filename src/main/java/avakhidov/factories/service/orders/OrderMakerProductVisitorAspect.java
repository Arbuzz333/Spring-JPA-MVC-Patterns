package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.exception.ClassArgumentIllegalException;
import avakhidov.factories.service.MainIngredient;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import avakhidov.factories.service.recipe.bun.BuckwheatBunRecipe;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static avakhidov.factories.utility.MainUtility.repeat;

@Aspect
@Configuration
public class OrderMakerProductVisitorAspect {

    @Value("#{new Double('${weight_bun}')}")
    private double weightBun;

    private Map<Class<? extends Product<? extends MainIngredient>>, BuckwheatBunRecipe> specifyClasses;

    public OrderMakerProductVisitorAspect(
            BuckwheatBunRecipe buckwheatBunRecipe) {
        specifyClasses = Map.of(BuckwheatBun.class, buckwheatBunRecipe);
//        VealCutlet.class, MuttonCutlet.class
    }

    /*ToDo put to DB*/
    private Map<Class, Integer> classIntegerMapRequest = new HashMap<>();
    private Map<Class, Integer> classIntegerMapRequestReady = new HashMap<>();
    private Map<Class, Integer> classIntegerMapRequestDecline = new HashMap<>();

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderMakerProductVisitorAspect.class);

    @Before("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerBunVisitor.makeOrdersProduct(..))")
    public void beforeMakeOrdersBun(JoinPoint joinPoint) {
        saveRequestProductQuantity(joinPoint, classIntegerMapRequest);
    }

    @Before("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void beforeMakeOrdersCutlet(JoinPoint joinPoint) {
        saveRequestProductQuantity(joinPoint, classIntegerMapRequest);
    }

    @Before("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerPancakeVisitor.makeOrdersProduct(..))")
    public void beforeMakeOrdersPancake(JoinPoint joinPoint) {
        saveRequestProductQuantity(joinPoint, classIntegerMapRequest);
    }

    @AfterReturning("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerBunVisitor.makeOrdersProduct(..))")
    public void afterReturningMakeOrdersBun(JoinPoint joinPoint) {
        saveRequestSuccessProductQuantity(joinPoint, classIntegerMapRequestReady);
    }

    @AfterReturning("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void afterReturningMakeOrdersCutlet(JoinPoint joinPoint) {
        saveRequestSuccessProductQuantity(joinPoint, classIntegerMapRequestReady);
    }

    @AfterReturning("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerPancakeVisitor.makeOrdersProduct(..))")
    public void afterReturningMakeOrdersPancake(JoinPoint joinPoint) {
        saveRequestSuccessProductQuantity(joinPoint, classIntegerMapRequestReady);
    }

    @AfterThrowing("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerBunVisitor.makeOrdersProduct(..))")
    public void afterThrowingMakeOrdersBun(JoinPoint joinPoint) {
        saveDeclineProductQuantity(joinPoint, classIntegerMapRequestDecline);
    }

    @AfterThrowing("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void afterThrowingMakeOrdersCutlet(JoinPoint joinPoint) {
        saveDeclineProductQuantity(joinPoint, classIntegerMapRequestDecline);
    }

    @AfterThrowing("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerPancakeVisitor.makeOrdersProduct(..))")
    public void afterThrowingMakeOrdersPancake(JoinPoint joinPoint) {
        saveDeclineProductQuantity(joinPoint, classIntegerMapRequestDecline);
    }

    @Around("@annotation(avakhidov.factories.annotations.SeeingSpecifyProductClass)")
    public Object toMakePancake(ProceedingJoinPoint joinPoint) throws Throwable {

        List<Product> products = new ArrayList<>();
        OrdersMakerProduct aThis = (OrdersMakerProduct) joinPoint.getTarget();
        try {
            if (specifyClasses.containsKey(aThis.getProductClazz())) {
                saveProductQuantity(classIntegerMapRequest, aThis.getProductClazz(), aThis.getQuantity());
                logger.info("Saved request for Product is: {} . {} . {} .", classIntegerMapRequest.values().stream().reduce(0, Integer::sum),
                        aThis.getProductClazz(), aThis.getQuantity());

                Recipe recipe = specifyClasses.get(aThis.getProductClazz());
                repeat(aThis.getQuantity(), () -> products.add((Product) recipe.cooked(weightBun)));

                saveProductQuantity(classIntegerMapRequestReady, aThis.getProductClazz(), aThis.getQuantity());
                logger.info("Saved success request for Product is: {} . {} . {} .", classIntegerMapRequestReady.values().stream().reduce(0, Integer::sum),
                        aThis.getProductClazz(), aThis.getQuantity());
            } else {
                return joinPoint.proceed();
            }
        } catch (ClassArgumentIllegalException ar) {
            logger.error("ClassArgumentIllegalException annotated method SeeingSpecifyProductClass");
        } catch (Exception e) {
            saveProductQuantity(classIntegerMapRequestDecline, aThis.getProductClazz(), aThis.getQuantity());
            logger.error("Saved error request for Product is: {} . {} . {} .", classIntegerMapRequestDecline.values().stream().reduce(0, Integer::sum),
                    aThis.getProductClazz(), aThis.getQuantity());
            throw e;
        }
        return products;
    }

    private void saveProductQuantity(Map<Class, Integer> map, Class productClazz, int quantity) {
        map.merge(productClazz, quantity, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
    }

    private void saveRequestProductQuantity(JoinPoint joinPoint, Map<Class, Integer> map) {
        Object[] args = joinPoint.getArgs();
        OrdersMakerProduct makerProduct = (OrdersMakerProduct) args[0];
        Class productClazz = makerProduct.getProductClazz();
        int quantity = makerProduct.getQuantity();
        saveProductQuantity(map, productClazz, quantity);
        logger.info("Saved request for Product is: {} . {} . {} .", map.values().stream().reduce(0, Integer::sum), productClazz, quantity);
    }

    private void saveRequestSuccessProductQuantity(JoinPoint joinPoint, Map<Class, Integer> map) {
        Object[] args = joinPoint.getArgs();
        OrdersMakerProduct makerProduct = (OrdersMakerProduct) args[0];
        Class productClazz = makerProduct.getProductClazz();
        int quantity = makerProduct.getQuantity();
        saveProductQuantity(map, productClazz, quantity);
        logger.info("Saved success request for Product is: {} . {} . {} .", map.values().stream().reduce(0, Integer::sum), productClazz, quantity);
    }

    private void saveDeclineProductQuantity(JoinPoint joinPoint, Map<Class, Integer> map) {
        Object[] args = joinPoint.getArgs();
        OrdersMakerProduct makerProduct = (OrdersMakerProduct) args[0];
        Class productClazz = makerProduct.getProductClazz();
        int quantity = makerProduct.getQuantity();
        saveProductQuantity(map, productClazz, quantity);
        logger.info("Saved decline request for Product is: {} . {} . {} .", map.values().stream().reduce(0, Integer::sum), productClazz, quantity);
    }

}
