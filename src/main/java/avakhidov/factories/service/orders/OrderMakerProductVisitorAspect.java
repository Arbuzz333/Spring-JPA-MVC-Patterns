package avakhidov.factories.service.orders;

import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderMakerProductVisitorAspect.class);

    @Before("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerBunVisitor.makeOrdersProduct(..))")
    public void beforeMakeOrdersBun(JoinPoint joinPoint) {
        saveProductQuantity(joinPoint, classIntegerMapRequest);
    }

    @Before("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void beforeMakeOrdersCutlet(JoinPoint joinPoint) {
        saveProductQuantity(joinPoint, classIntegerMapRequest);
    }

    @AfterReturning("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerBunVisitor.makeOrdersProduct(..))")
    public void afterReturningMakeOrdersBun(JoinPoint joinPoint) {
        saveProductQuantity(joinPoint, classIntegerMapRequestReady);
    }

    @AfterReturning("execution(* avakhidov.factories.service.orders.ordersvisitor.OrderMakerCutletVisitor.makeOrdersProduct(..))")
    public void afterReturningMakeOrdersCutlet(JoinPoint joinPoint) {
        saveProductQuantity(joinPoint, classIntegerMapRequestReady);
    }

    private void saveProductQuantity(JoinPoint joinPoint, Map<Class, Integer> map) {
        Object[] args = joinPoint.getArgs();
        OrdersMakerProduct makerProduct = (OrdersMakerProduct) args[0];
        map.merge(makerProduct.getProductClazz(), makerProduct.getQuantity(), new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        logger.info("Saved request for Product is: {} . {} .", map.values().stream().reduce(0, Integer::sum), map.getClass());
    }
}
