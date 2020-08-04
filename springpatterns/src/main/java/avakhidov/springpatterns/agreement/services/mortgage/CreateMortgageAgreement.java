package avakhidov.springpatterns.agreement.services.mortgage;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static avakhidov.springpatterns.agreement.entyties.Agreement.mortgage;




@Service
public class CreateMortgageAgreement implements CreateAgreement {

    @Override
    public Agreement createAgreement(String owner, BigDecimal sum) {
        Date date = new Date();
        return new Agreement(buildNumberMortgageNumber(), owner, date, new Payment(sum, date));
    }

    private String buildNumberMortgageNumber() {
        UUID uuid = UUID.randomUUID();
        return  mortgage + uuid;
    }
}
