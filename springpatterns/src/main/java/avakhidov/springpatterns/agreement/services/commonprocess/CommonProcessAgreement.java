package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.annotation.SelfAutowired;
import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.ProcessAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

import static avakhidov.springpatterns.agreement.entyties.Agreement.common;


@Service
public class CommonProcessAgreement implements ProcessAgreement {

    @SelfAutowired
    private CommonProcessAgreement myself;

    private TreeMap<String, Agreement> ownerAgreements = new TreeMap<>();

    @Override
    public void process(String owner, BigDecimal sum) {
        Optional<Agreement> optionalAgreement = myself.findAgreementByOwner(owner);
        if (optionalAgreement.isPresent()) {
            Agreement agreement = optionalAgreement.get();
            agreement.getPayment().setPaymentToList(sum);
        } else {
            Agreement newAgreement = new Agreement(buildNumber(), owner, new Payment(sum));
            myself.ownerAgreements.put(owner, newAgreement);
        }
    }

    @Override
    public Agreement findAgreement(String number) {
        return ownerAgreements.values().stream().filter(a -> a.getNumber().equals(number)).findFirst().orElse(null);
    }

    @Override
    public Optional<Agreement> findAgreementByOwner(String owner) {
        return Optional.ofNullable(ownerAgreements.get(owner));
    }

    private String buildNumber() {
        return common + UUID.randomUUID();
    }
}
