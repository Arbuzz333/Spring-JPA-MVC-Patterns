package avakhidov.anotations;

import avakhidov.config.TimesOfDayConditional;
import avakhidov.enumes.TimesOfDay;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(TimesOfDayConditional.class)
public @interface TimesOfDayModification {

    TimesOfDay mod();
}
