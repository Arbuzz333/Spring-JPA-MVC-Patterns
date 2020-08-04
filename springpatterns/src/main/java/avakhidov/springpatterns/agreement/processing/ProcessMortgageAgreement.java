package avakhidov.springpatterns.agreement.processing;

import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static avakhidov.springpatterns.agreement.entyties.Agreement.mortgage;




@Component
public class ProcessMortgageAgreement {

    private final StorageAgreement<String, Agreement> storage;

    private final CreateAgreement createAgreement;

    private final ReplenishAgreement replenishAgreement;

    public ProcessMortgageAgreement(
            ReplenishAgreement replenishAgreement,
            CreateAgreement createAgreement,
            StorageAgreement<String, Agreement> storage) {
        this.replenishAgreement = replenishAgreement;
        this.createAgreement = createAgreement;
        this.storage = storage;
    }

    public void process(String owner, BigDecimal sum) {

        Optional<Agreement> optionalAgreement = storage.findAgreement(owner);
        if (optionalAgreement.isEmpty()) {
            Agreement agreement = createAgreement.createAgreement(owner, sum);
            storage.saveAgreement(agreement);
        } else {
            Agreement agreement = optionalAgreement.get();
            if (agreement.getNumber().startsWith(mortgage)) {
                replenishAgreement.replenishment(agreement, sum);
            } else {
                throw new UnsupportedOperationException(agreement.getNumber());
            }
        }
    }

    public Agreement findAgreement(String number) {
        Agreement agreement = storage.getAgreement(number);
        if (agreement.getNumber().startsWith(mortgage)) {
            return agreement;
        } else {
            throw new UnsupportedOperationException(agreement.getNumber());
        }
    }

    public Optional<Agreement> findAgreementByOwner(String owner) {
        Optional<Agreement> agreement = storage.findAgreement(owner);
        return agreement;
    }

}
