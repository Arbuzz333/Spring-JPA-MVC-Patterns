package avakhidov.springpatterns.agreement.services.carloan;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;



@Service
public class ReplenishCarLoanAgreement implements ReplenishAgreement {

    @Override
    public void replenishment(Agreement agreement, BigDecimal sum) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        agreement.getPayment().getPayingList().add(new Payment(sum, new Date(calendar.getTimeInMillis())));
    }
}
