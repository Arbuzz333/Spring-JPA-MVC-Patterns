package avakhidov.main;


import avakhidov.anotations.MainRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;




public class MainRunnerListener {

    private static final Logger LOG = LoggerFactory.getLogger(MainRunnerListener.class);

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @EventListener
    public void handlerContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null) {
                continue;
            }
            Class<?> beanClass = ClassUtils.resolveClassName(beanClassName, ClassLoader.getSystemClassLoader());
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MainRunner.class)) {
                    Object bean = applicationContext.getBean(beanDefinitionName);
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        LOG.debug("MainRunner have exception by access", e);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        LOG.debug("MainRunner have exception by invocation", e);
                    }
                }
            }

        }
    }
}
