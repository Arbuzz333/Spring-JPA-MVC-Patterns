package avakhidov.factories.annotations;


import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.service.orders.OrdersSplitter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(METHOD)
public @interface KitchenCutlet {

    Class<?> ordersClass() default OrdersSplitter.class;

    Class<?> productClass() default Product.class;

    KindMeat kindMeat() default KindMeat.CHICKEN;

    FatMeat fatMeat() default FatMeat.MEDIUM_FAT;

}
