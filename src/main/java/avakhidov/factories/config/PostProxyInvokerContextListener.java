package avakhidov.factories.config;

import avakhidov.factories.annotations.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum.CREATE_MUTTON_CUTLET;
import static avakhidov.factories.cache.CacheParameters.FIRST_CUTLET;

@Component
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            if (originalClassName != null) {
                try {
                    Class<?> originalClass = Class.forName(originalClassName);
                    Method[] methods = originalClass.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(PostProxy.class)) {
                            Object bean = context.getBean(name);
                            Class<?> currentClass = bean.getClass();
                            try {
                                Method currentMethod = currentClass.getMethod(method.getName(), method.getParameterTypes());
                                try {
                                    currentMethod.invoke(bean, FIRST_CUTLET.getName(), CREATE_MUTTON_CUTLET.getId());
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
