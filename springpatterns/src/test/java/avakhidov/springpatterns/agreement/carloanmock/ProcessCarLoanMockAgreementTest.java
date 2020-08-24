package avakhidov.springpatterns.agreement.carloanmock;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.mortgage.StorageContext;
import avakhidov.springpatterns.agreement.services.carloan.ProcessCarLoanAgreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;




@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = StorageContext.class),
        @ContextConfiguration(classes = ProcessCarLoanMockAgreementContext.class)
})
public class ProcessCarLoanMockAgreementTest {

    @Autowired
    private ProcessCarLoanAgreement processCarLoanMockAgreementTest;

    @Test
    public void process() {
        processCarLoanMockAgreementTest.process("OwnerMortgage", new BigDecimal(777));
        processCarLoanMockAgreementTest.process("OwnerMortgage", new BigDecimal(555));
        Optional<Agreement> optionalAgreement = processCarLoanMockAgreementTest.findAgreementByOwner("OwnerMortgage");

        assertTrue(optionalAgreement.isPresent());
        assertEquals(optionalAgreement.get().getPayment().getSum(), new BigDecimal(555));
    }
}