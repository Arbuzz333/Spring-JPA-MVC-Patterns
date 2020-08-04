package avakhidov.springpatterns.agreement.services;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.math.BigDecimal;



public interface CreateAgreement {

    Agreement createAgreement(String owner, BigDecimal sum);
}
