package avakhidov.factories.market;

import avakhidov.factories.entity.Product;

import java.math.BigInteger;

public interface Market<T extends Product> {

    int orderQuantity();

    Market<T> setQuantity(int quantity);

    T getProduct();

    BigInteger getQuantity();

}
