package avakhidov.springpatterns.agreement.commonappprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.commonprocess.CommonProcessAgreement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;




@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonProcessAgreementTest {

    public static final String ownerFirst = "ownerFirst";

    @Autowired
    private CommonProcessAgreement commonProcessAgreement;

    @Test
    public void process() {

        commonProcessAgreement.process(ownerFirst, new BigDecimal(77));

        Optional<Agreement> agreementByOwner = commonProcessAgreement.findAgreementByOwner(ownerFirst);
        assertTrue("", agreementByOwner.isPresent());
        Agreement agreement = agreementByOwner.get();
        assertEquals("", new BigDecimal(77), agreement.getPayment().getSum());

        Agreement agreementThousand = commonProcessAgreement.findAgreement("1000");
        assertEquals("", "owner_big", agreementThousand.getOwner());

        Optional<Agreement> owner_sevenOptional = commonProcessAgreement.findAgreementByOwner("owner_seven");
        assertTrue("", owner_sevenOptional.isPresent());
        assertEquals("", "777", owner_sevenOptional.get().getNumber());

        commonProcessAgreement.process("owner_cmall", new BigDecimal(1200));
        Optional<Agreement> owner_cmallOptional = commonProcessAgreement.findAgreementByOwner("owner_cmall");
        assertTrue("", owner_cmallOptional.isPresent());
        assertEquals("", new BigDecimal(1200), owner_cmallOptional.get().getPayment().getPayingList().get(0).getSum());
    }
}