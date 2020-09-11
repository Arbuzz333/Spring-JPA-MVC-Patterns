package avakhidov.springpatterns.contract.config;

import avakhidov.springpatterns.contract.services.ConfectioneryFactory;
import avakhidov.springpatterns.contract.services.Contract;
import avakhidov.springpatterns.contract.entyties.BuildingContract;
import avakhidov.springpatterns.contract.entyties.Currency;
import avakhidov.springpatterns.contract.entyties.CurrencyAmerica;
import avakhidov.springpatterns.contract.entyties.MeatContract;
import avakhidov.springpatterns.contract.services.MilkFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class ContractConfig {

    @Bean
    public Contract<CurrencyAmerica> americaContract() {
        return new MeatContract();
    }

    @Bean
    public Contract<Currency> contract() {
        return new BuildingContract();
    }

    @Bean
    public ConfectioneryFactory<CurrencyAmerica> confectioneryFactory() {
        return new ConfectioneryFactory<>(americaContract());
    }

    @Bean
    public MilkFactory<CurrencyAmerica> milkFactory(Contract<CurrencyAmerica> americaContract) {
        return new MilkFactory<>();
    }

}
