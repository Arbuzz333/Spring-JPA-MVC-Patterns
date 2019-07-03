package avakhidov.factories.service;

import avakhidov.factories.entity.Product;

public interface Oven<T> {

    Product<T> toBake(T prepack);
}
