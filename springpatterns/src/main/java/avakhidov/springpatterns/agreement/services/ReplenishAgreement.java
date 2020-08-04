package avakhidov.springpatterns.agreement.services;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.math.BigDecimal;



public interface ReplenishAgreement {

    void replenishment(Agreement agreement, BigDecimal sum);
}
