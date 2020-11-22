package avakhidov.factories.config;

import avakhidov.factories.adapters.OvenToGrill;
import avakhidov.factories.kitchen.KitchenFreezer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class KitchenFreezerBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(OvenToGrill.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().equals(KitchenFreezer.class)) {
            KitchenFreezer kitchenFreezer = (KitchenFreezer) bean;
            kitchenFreezer.fillTheFreezeIsEmpty();
        } else if (bean.getClass().equals(OvenToGrill.class)) {
            logger.info("Bean KitchenFreezer is created");
        }
        return bean;
    }
}
