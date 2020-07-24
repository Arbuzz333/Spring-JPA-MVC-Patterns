package avakhidov.main;

import avakhidov.anotations.MainRunner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

public class BeanDefinitionAppenderBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory factory;

     public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
         Arrays.stream(factory.getBeanDefinitionNames()).parallel().forEach(name -> {
             if (neededMainBehaviour(bean)) {
                 BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
                 if (beanDefinition.getBeanClassName() == null) {
                     beanDefinition.setBeanClassName(bean.getClass().getCanonicalName());
                 }

             }
         });

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         return bean;
    }


    private boolean neededMainBehaviour(Object name) {
        return Arrays.stream(name.getClass().getMethods()).anyMatch(method -> method.isAnnotationPresent(MainRunner.class));
    }

}
