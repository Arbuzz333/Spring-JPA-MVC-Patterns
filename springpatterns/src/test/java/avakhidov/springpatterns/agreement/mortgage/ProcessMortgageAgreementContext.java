package avakhidov.springpatterns.agreement.mortgage;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.services.mortgage.ProcessMortgageAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;




@TestConfiguration
@ComponentScan("avakhidov.springpatterns.agreement.services.mortgage")
public class ProcessMortgageAgreementContext {

    @Autowired
    private ReplenishAgreement replenishMortgageAgreement;

    @Autowired
    private CreateAgreement createMortgageAgreement;

    @Autowired
    StorageAgreement<String, Agreement> storage;

    @Bean
    ProcessMortgageAgreement processMortgageAgreementTest() {
        return new ProcessMortgageAgreement(replenishMortgageAgreement, createMortgageAgreement, storage);
    }

}
