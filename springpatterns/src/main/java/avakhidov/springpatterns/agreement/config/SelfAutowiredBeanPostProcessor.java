package avakhidov.springpatterns.agreement.config;

import avakhidov.springpatterns.agreement.annotation.SelfAutowired;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;



@Component
public class SelfAutowiredBeanPostProcessor implements BeanPostProcessor, Ordered {

    private Map<String, Object> stringObjectMap = new TreeMap<>();
    private Map<String, Field> stringFieldsMap = new TreeMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(SelfAutowired.class)) {
                stringObjectMap.put(beanName, bean);
                stringFieldsMap.put(beanName, field);
                break;
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object origin = stringObjectMap.get(beanName);
        if (origin != null) {
            Field field = stringFieldsMap.get(beanName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, origin, bean);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
