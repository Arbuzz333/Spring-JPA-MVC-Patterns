package avakhidov.factories.config;

import avakhidov.factories.service.orders.OrdersMaker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class OrdersMakerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().equals(OrdersMaker.class)) {
            OrdersMaker kitchenFreezer = (OrdersMaker) bean;
            try {
                kitchenFreezer.initMapClassMethodHandler();
            } catch (NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
