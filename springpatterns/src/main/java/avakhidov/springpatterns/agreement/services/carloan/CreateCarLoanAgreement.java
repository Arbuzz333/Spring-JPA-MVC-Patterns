package avakhidov.springpatterns.agreement.services.carloan;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static avakhidov.springpatterns.agreement.entyties.Agreement.car_loan;




@Service
public class CreateCarLoanAgreement implements CreateAgreement {

    private long number = 100000000000L;

    @Override
    public Agreement createAgreement(String owner, BigDecimal sum) {
        Date date = new Date();
        return new Agreement(buildNumberMortgageNumber(), owner, date, new Payment(sum, date));
    }

    private String buildNumberMortgageNumber() {
        return  car_loan + number++;
    }
}
