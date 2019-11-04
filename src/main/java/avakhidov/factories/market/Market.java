package avakhidov.factories.market;

import avakhidov.factories.entity.Product;

public interface Market<T extends Product> {

    int orderQuantity();

    int getMaxQuantity();
}
