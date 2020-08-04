package avakhidov.springpatterns.agreement.services.mortgage;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;




@Service
public class ReplenishMortgageAgreement implements ReplenishAgreement {

    @Override
    public void replenishment(Agreement agreement, BigDecimal sum) {
        agreement.getPayment().getPayingList().add(new Payment(sum, new Date()));
    }
}
