package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.annotation.PostInitialize;
import avakhidov.springpatterns.agreement.annotation.SelfAutowired;
import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.services.ProcessAgreement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static avakhidov.springpatterns.agreement.entyties.Agreement.common;



@Service
public class CommonProcessAgreement implements ProcessAgreement {

    private final StorageAgreementCommonMemory storageCommon;

    @SelfAutowired
    private CommonProcessAgreement myself;

    private TreeMap<String, Agreement> ownerAgreements = new TreeMap<>();

    public CommonProcessAgreement(
            StorageAgreementCommonMemory storageCommon) {
        this.storageCommon = storageCommon;
    }

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

    @PostInitialize
    public void fillOwnerAgreements() {
        PriorityQueue<Agreement> agreementsLimit = storageCommon.getAgreementsLimit(3);
        ownerAgreements.putAll(agreementsLimit.stream().collect(Collectors.toMap(Agreement::getOwner, a -> a)));
    }

    private String buildNumber() {
        return common + UUID.randomUUID();
    }
}
