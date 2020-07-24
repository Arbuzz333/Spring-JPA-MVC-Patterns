package avakhidov.config;


import avakhidov.main.BeanDefinitionAppenderBeanPostProcessor;
import avakhidov.main.MainRunnerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMainRunner {

    @Bean
    public BeanDefinitionAppenderBeanPostProcessor beanDefinitionAppenderBeanPostProcessor() {
        return new BeanDefinitionAppenderBeanPostProcessor();
    }

    @Bean
    public MainRunnerListener mainRunnerListener() {
        return new MainRunnerListener();
    }
}
