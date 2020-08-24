package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static avakhidov.springpatterns.agreement.services.commonprocess.CommonProcessAgreementTest.ownerFirst;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonProcessAgreementPostInitializeApplicationTest {

    @Autowired
    private CommonProcessAgreement commonProcessAgreement;

    @Test
    public void process() {

        commonProcessAgreement.process(ownerFirst, new BigDecimal(77));

        Optional<Agreement> agreementByOwner = commonProcessAgreement.findAgreementByOwner(ownerFirst);
        assertTrue("", agreementByOwner.isPresent());
        Agreement agreement = agreementByOwner.get();
        assertEquals("", new BigDecimal(77), agreement.getPayment().getSum());

        Agreement agreementByNumber = commonProcessAgreement.findAgreement(agreement.getNumber());
        assertEquals("", ownerFirst, agreementByNumber.getOwner());

        assertTrue("", agreementByNumber.getPayment().getPayingList().isEmpty());

        commonProcessAgreement.process(ownerFirst, new BigDecimal(55));
        assertEquals("", new BigDecimal(55), agreementByNumber.getPayment().getPayingList().get(0).getSum());
    }
}
