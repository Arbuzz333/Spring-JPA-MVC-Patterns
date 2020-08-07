package avakhidov.springpatterns.agreement.services;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.math.BigDecimal;
import java.util.Optional;




public interface ProcessAgreement {

    void process(String owner, BigDecimal sum);

    Agreement findAgreement(String number);

    Optional<Agreement> findAgreementByOwner(String owner);

}
