package avakhidov.factories.annotations;


import avakhidov.factories.service.orders.OrdersSplitter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(METHOD)
public @interface KitchenFreezerAspect {

    String  mainIngredientEnum() default "MEAT";

    Class<?> ordersClass() default OrdersSplitter.class;

}
