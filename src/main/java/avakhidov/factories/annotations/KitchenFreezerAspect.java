package avakhidov.factories.annotations;


import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.orders.OrdersSplitter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(METHOD)
public @interface KitchenFreezerAspect {

    Class<?> ordersClass() default OrdersSplitter.class;

    Class<?> productClass() default Product.class;

    KindFlour kindFlour() default KindFlour.WHEAT;

    KindDough kindDough() default KindDough.YEAST_DOUGH;

}
