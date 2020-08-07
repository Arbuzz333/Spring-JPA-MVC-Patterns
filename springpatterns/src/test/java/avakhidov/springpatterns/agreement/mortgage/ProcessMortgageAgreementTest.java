package avakhidov.springpatterns.agreement.mortgage;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.mortgage.ProcessMortgageAgreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static avakhidov.springpatterns.agreement.mortgage.StorageContext.test_system;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;





@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = StorageContext.class),
        @ContextConfiguration(classes = ProcessMortgageAgreementContext.class)
})
public class ProcessMortgageAgreementTest {

    @Autowired
    private ProcessMortgageAgreement processMortgageAgreementTest;

    @Test
    public void process() {

        processMortgageAgreementTest.process("OwnerMortgage", new BigDecimal(445));
        processMortgageAgreementTest.process("OwnerSecond", new BigDecimal(225));
        Agreement agreement = processMortgageAgreementTest.findAgreement(test_system + "7771888");
        Optional<Agreement> optionalAgreement = processMortgageAgreementTest.findAgreementByOwner("OwnerSecond");

        List<Payment> payingList = agreement.getPayment().getPayingList();
        Optional<Payment> any = payingList.stream().filter(p -> p.getSum().equals(new BigDecimal(445))).findAny();

        assertTrue(any.isPresent());
        assertTrue(optionalAgreement.isPresent());
        assertEquals(optionalAgreement.get().getPayment().getSum(), new BigDecimal(225));
        assertTrue(optionalAgreement.get().getPayment().getPayingList().isEmpty());

    }
}