package avakhidov.springpatterns.agreement.carloanmock;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.services.carloan.ProcessCarLoanAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;




@TestConfiguration
public class ProcessCarLoanMockAgreementContext {

    @MockBean
    private ReplenishAgreement replenishCarLoanAgreement;

    @MockBean
    private CreateAgreement createCarLoanAgreement;

    @Autowired
    StorageAgreement<String, Agreement> storage;

    @Bean
    ProcessCarLoanAgreement processCarLoanMockAgreementTest() {
        return new ProcessCarLoanAgreement(replenishCarLoanAgreement, createCarLoanAgreement, storage);
    }

}
