package avakhidov.springpatterns.config;


import avakhidov.springpatterns.flour.FlourObjectFactory;
import avakhidov.springpatterns.flour.FlourObjectFactoryImpl;
import avakhidov.springpatterns.flour.FlourObjectSecondFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class AppSPConfig {

    @Bean
    public FlourObjectFactory firstFactory() {
        return new FlourObjectFactoryImpl();
    }


    @Bean
    public FlourObjectFactory secondFactory() {
        return new FlourObjectSecondFactoryImpl();
    }

}
