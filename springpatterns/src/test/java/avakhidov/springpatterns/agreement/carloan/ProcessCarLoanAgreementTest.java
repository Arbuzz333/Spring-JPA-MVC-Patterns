package avakhidov.springpatterns.agreement.carloan;


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
        @ContextConfiguration(classes = ProcessCarLoanAgreementContext.class)
})
public class ProcessCarLoanAgreementTest {

    @Autowired
    private ProcessCarLoanAgreement processCarLoanAgreementTest;

    @Test
    public void process() {

        processCarLoanAgreementTest.process("OwnerCarLoan", new BigDecimal(777));
        processCarLoanAgreementTest.process("OwnerCarLoan", new BigDecimal(555));
        processCarLoanAgreementTest.process("OwnerSecondCarLoan", new BigDecimal(555));
        Optional<Agreement> optionalAgreement = processCarLoanAgreementTest.findAgreementByOwner("OwnerCarLoan");
        Optional<Agreement> optionalSecond = processCarLoanAgreementTest.findAgreementByOwner("OwnerSecondCarLoan");


        assertTrue(optionalAgreement.isPresent());
        assertEquals(optionalAgreement.get().getPayment().getSum(), new BigDecimal(777));
        assertEquals(optionalAgreement.get().getPayment().getPayingList().get(0).getSum(), new BigDecimal(555));
        assertTrue(optionalSecond.get().getPayment().getPayingList().isEmpty());

    }
}