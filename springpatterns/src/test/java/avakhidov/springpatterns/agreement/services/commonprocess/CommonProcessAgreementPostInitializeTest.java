package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static avakhidov.springpatterns.agreement.services.commonprocess.CommonProcessAgreementTest.ownerFirst;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;




@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = CommonStorageContext.class),
        @ContextConfiguration(classes = CommonProcessAgreementContext.class)
})
public class CommonProcessAgreementPostInitializeTest {

    @Autowired
    private CommonProcessAgreement commonProcessAgreementTest;

    @Test
    public void process() {

        commonProcessAgreementTest.process(ownerFirst, new BigDecimal(77));

        Optional<Agreement> agreementByOwner = commonProcessAgreementTest.findAgreementByOwner(ownerFirst);
        assertTrue("", agreementByOwner.isPresent());
        Agreement agreement = agreementByOwner.get();
        assertEquals("", new BigDecimal(77), agreement.getPayment().getSum());

        Agreement agreementByNumber = commonProcessAgreementTest.findAgreement(agreement.getNumber());
        assertEquals("", ownerFirst, agreementByNumber.getOwner());

        assertTrue("", agreementByNumber.getPayment().getPayingList().isEmpty());

        commonProcessAgreementTest.process(ownerFirst, new BigDecimal(55));
        assertEquals("", new BigDecimal(55), agreementByNumber.getPayment().getPayingList().get(0).getSum());
    }
}
