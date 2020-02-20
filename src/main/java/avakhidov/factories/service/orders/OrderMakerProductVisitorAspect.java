package avakhidov.factories.service.orders;

import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Aspect
@Configuration
public class OrderMakerProductVisitorAspect {

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
        saveDeclineSuccessProductQuantity(joinPoint, classIntegerMapRequestDecline);
    }

    @AfterThrowing("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void afterThrowingMakeOrdersCutlet(JoinPoint joinPoint) {
        saveDeclineSuccessProductQuantity(joinPoint, classIntegerMapRequestDecline);
    }

    @AfterThrowing("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerPancakeVisitor.makeOrdersProduct(..))")
    public void afterThrowingMakeOrdersPancake(JoinPoint joinPoint) {
        saveDeclineSuccessProductQuantity(joinPoint, classIntegerMapRequestDecline);
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

    private void saveDeclineSuccessProductQuantity(JoinPoint joinPoint, Map<Class, Integer> map) {
        Object[] args = joinPoint.getArgs();
        OrdersMakerProduct makerProduct = (OrdersMakerProduct) args[0];
        Class productClazz = makerProduct.getProductClazz();
        int quantity = makerProduct.getQuantity();
        saveProductQuantity(map, productClazz, quantity);
        logger.info("Saved decline request for Product is: {} . {} . {} .", map.values().stream().reduce(0, Integer::sum), productClazz, quantity);
    }

}
