package avakhidov.springpatterns.agreement.processing;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static avakhidov.springpatterns.agreement.entyties.Agreement.mortgage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;





@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProcessMortgageAgreementContext.class)
public class ProcessMortgageAgreementTest {

    @Autowired
    private ProcessMortgageAgreement processMortgageAgreementTest;

    @Test
    public void process() {

        processMortgageAgreementTest.process("OwnerMortgage", new BigDecimal(445));
        processMortgageAgreementTest.process("OwnerSecond", new BigDecimal(225));
        Agreement agreement = processMortgageAgreementTest.findAgreement(mortgage + "7771888");
        Optional<Agreement> optionalAgreement = processMortgageAgreementTest.findAgreementByOwner("OwnerSecond");

        assertEquals(agreement.getPayment().getPayingList().get(0).getSum(), new BigDecimal(445));
        assertTrue(optionalAgreement.isPresent());
        assertEquals(optionalAgreement.get().getPayment().getSum(), new BigDecimal(225));
        assertTrue(optionalAgreement.get().getPayment().getPayingList().isEmpty());

    }
}