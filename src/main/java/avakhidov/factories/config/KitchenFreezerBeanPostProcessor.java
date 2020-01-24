package avakhidov.factories.config;

import avakhidov.factories.kitchen.KitchenFreezer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class KitchenFreezerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().equals(KitchenFreezer.class)) {
            KitchenFreezer kitchenFreezer = (KitchenFreezer) bean;
            kitchenFreezer.fillTheFreezeIsEmpty();
        }
        return bean;
    }
}
