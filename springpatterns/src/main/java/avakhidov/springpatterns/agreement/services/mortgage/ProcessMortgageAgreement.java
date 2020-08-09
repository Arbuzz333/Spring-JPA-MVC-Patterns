package avakhidov.springpatterns.agreement.services.mortgage;

import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ProcessAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;




@Service
public class ProcessMortgageAgreement implements ProcessAgreement {

    private final StorageAgreement<String, Agreement> storageMortgage;

    private final CreateAgreement createMortgageAgreement;

    private final ReplenishAgreement replenishMortgageAgreement;

    public ProcessMortgageAgreement(
            ReplenishAgreement replenishMortgageAgreement,
            CreateAgreement createMortgageAgreement,
            StorageAgreement<String, Agreement> storageMortgage) {
        this.replenishMortgageAgreement = replenishMortgageAgreement;
        this.createMortgageAgreement = createMortgageAgreement;
        this.storageMortgage = storageMortgage;
    }

    @Override
    public void process(String owner, BigDecimal sum) {

        Optional<Agreement> optionalAgreement = storageMortgage.findAgreement(owner);
        if (optionalAgreement.isEmpty()) {
            Agreement agreement = createMortgageAgreement.createAgreement(owner, sum);
            storageMortgage.saveAgreement(agreement);
        } else {
            Agreement agreement = optionalAgreement.get();
            replenishMortgageAgreement.replenishment(agreement, sum);
        }
    }

    @Override
    public Agreement findAgreement(String number) {
        Agreement agreement = storageMortgage.getAgreement(number);
        return agreement;
    }

    @Override
    public Optional<Agreement> findAgreementByOwner(String owner) {
        Optional<Agreement> agreement = storageMortgage.findAgreement(owner);
        return agreement;
    }

}
