package avakhidov.springpatterns.agreement.services.carloan;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.CreateAgreement;
import avakhidov.springpatterns.agreement.services.ProcessAgreement;
import avakhidov.springpatterns.agreement.services.ReplenishAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;



@Service
public class ProcessCarLoanAgreement implements ProcessAgreement {

    private final StorageAgreement<String, Agreement> storageCarLoan;

    private final CreateAgreement createCarLoanAgreement;

    private final ReplenishAgreement replenishCarLoanAgreement;

    public ProcessCarLoanAgreement(
            ReplenishAgreement replenishCarLoanAgreement,
            CreateAgreement createCarLoanAgreement,
            StorageAgreement<String, Agreement> storageCarLoan) {
        this.replenishCarLoanAgreement = replenishCarLoanAgreement;
        this.createCarLoanAgreement = createCarLoanAgreement;
        this.storageCarLoan = storageCarLoan;
    }

    @Override
    public void process(String owner, BigDecimal sum) {

        Optional<Agreement> optionalAgreement = storageCarLoan.findAgreement(owner);
        if (optionalAgreement.isEmpty()) {
            Agreement agreement = createCarLoanAgreement.createAgreement(owner, sum);
            storageCarLoan.saveAgreement(agreement);
        } else {
            Agreement agreement = optionalAgreement.get();
            replenishCarLoanAgreement.replenishment(agreement, sum);
        }
    }

    @Override
    public Agreement findAgreement(String number) {
        Agreement agreement = storageCarLoan.getAgreement(number);
        return agreement;
    }

    @Override
    public Optional<Agreement> findAgreementByOwner(String owner) {
        Optional<Agreement> agreement = storageCarLoan.findAgreement(owner);
        return agreement;
    }

}
