package avakhidov.factories.service;

import avakhidov.factories.service.serviceimpl.OvenWorksImpl;

import java.math.BigDecimal;

public interface OvenWorks<T> {

    T toBake();

    Oven.ParamsOven getParams();

    BigDecimal getMinPartyIngredient(int quantity);

    double getMinPartyProduct();

    OvenWorksImpl setOven(Oven<T> oven);

}
