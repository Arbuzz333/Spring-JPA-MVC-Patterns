package avakhidov.springpatterns.agreement.carloan;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.services.carloan.ProcessCarLoanAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;




@TestConfiguration
@ComponentScan("avakhidov.springpatterns.agreement.services.carloan")
public class ProcessCarLoanAgreementContext {

    @Autowired
    private ReplenishAgreement replenishCarLoanAgreement;

    @Autowired
    private CreateAgreement createCarLoanAgreement;

    @Autowired
    StorageAgreement<String, Agreement> storage;

    @Bean
    ProcessCarLoanAgreement processCarLoanAgreementTest() {
        return new ProcessCarLoanAgreement(replenishCarLoanAgreement, createCarLoanAgreement, storage);
    }

}
