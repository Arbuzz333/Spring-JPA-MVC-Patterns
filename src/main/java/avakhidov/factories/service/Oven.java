package avakhidov.factories.service;

import avakhidov.factories.entity.Product;

import java.util.Map;

public interface Oven<T> {

    Product<T> toBake(T prepack);

    Map<OvenSituation, Integer> getParams();

    enum OvenSituation {
        HOLD,
        HOT
    }
}
