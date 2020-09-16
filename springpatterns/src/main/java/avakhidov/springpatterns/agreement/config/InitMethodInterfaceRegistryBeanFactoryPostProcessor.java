package avakhidov.springpatterns.agreement.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;


@Component
public class InitMethodInterfaceRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null) {
                beanClassName = BeanDefinitionUtils.creatClassNameForJavaConfig(beanDefinition);
            }
            try {
                Class<?> beanClass = Class.forName(beanClassName);
                Class<?>[] allInterfacesForClass = ClassUtils.getAllInterfacesForClass(beanClass);
                for (Class<?> interfacesForClass : allInterfacesForClass) {
                    Method[] methods = interfacesForClass.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(PostConstruct.class)) {
                            beanDefinition.setInitMethodName(method.getName());
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
