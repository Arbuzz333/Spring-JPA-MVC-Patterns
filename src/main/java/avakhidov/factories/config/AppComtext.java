package avakhidov.factories.config;


import avakhidov.factories.kitchen.KitchenFreezer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppContext {
    @Bean(initMethod = "setup", destroyMethod = "cleanup")
    public KitchenFreezer course() {
        return new KitchenFreezer();
    }

}
