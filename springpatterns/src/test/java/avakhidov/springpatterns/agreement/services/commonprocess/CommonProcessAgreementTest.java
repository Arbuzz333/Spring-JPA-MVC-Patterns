package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;




@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonProcessAgreementContext.class)
public class CommonProcessAgreementTest {

    public static final String ownerFirst = "ownerFirst";

    @Autowired
    private CommonProcessAgreement processAgreement;

    @Test
    public void process() {

        processAgreement.process(ownerFirst, new BigDecimal(77));

        Optional<Agreement> agreementByOwner = processAgreement.findAgreementByOwner(ownerFirst);
        assertTrue("", agreementByOwner.isPresent());
        Agreement agreement = agreementByOwner.get();
        assertEquals("", new BigDecimal(77), agreement.getPayment().getSum());

        Agreement agreementByNumber = processAgreement.findAgreement(agreement.getNumber());
        assertEquals("", ownerFirst, agreementByNumber.getOwner());

        assertTrue("", agreementByNumber.getPayment().getPayingList().isEmpty());

        processAgreement.process(ownerFirst, new BigDecimal(55));
        assertEquals("", new BigDecimal(55), agreementByNumber.getPayment().getPayingList().get(0).getSum());
    }
}