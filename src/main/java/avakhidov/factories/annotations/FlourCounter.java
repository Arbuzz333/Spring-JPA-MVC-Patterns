package avakhidov.factories.annotations;

import avakhidov.factories.enums.KindFlour;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(FlourContainer.class)
public @interface FlourCounter {

    KindFlour kindFlour();
}
