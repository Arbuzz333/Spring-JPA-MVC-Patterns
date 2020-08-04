package avakhidov.springpatterns.agreement.processing;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;




@Configuration
@ComponentScan("avakhidov.springpatterns.agreement.services.mortgage")
@Import(StorageContext.class)
public class ProcessMortgageAgreementContext {

    @Autowired
    private ReplenishAgreement replenishAgreement;

    @Autowired
    private CreateAgreement createAgreement;

    @Autowired
    StorageAgreement<String, Agreement> storage;

    @Bean
    ProcessMortgageAgreement processMortgageAgreementTest() {
        return new ProcessMortgageAgreement(replenishAgreement, createAgreement, storage);
    }

}
