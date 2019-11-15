package avakhidov.factories.service;

import java.math.BigDecimal;

public interface OvenWorks<T> {

    T toBake();

    Oven.ParamsOven getParams();

    BigDecimal getMinPartyIngredient();

    double getMinPartyProduct();

}
