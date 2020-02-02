package avakhidov.factories.kitchen;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class KitchenAspect  {

    @Pointcut("@annotation(avakhidov.factories.annotations.KitchenFreezerAspect)")
    public void annotatedKitchenFreezerAspect() {

    }

    @Before("annotatedKitchenFreezerAspect()")
    public void printABit() {
    }

}

