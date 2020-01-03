package avakhidov.factories.market;

import avakhidov.factories.entity.Product;

import java.math.BigInteger;

public class GroceryStore<T extends Product> implements Market<T> {

    private T product;
    private int quantity;

    public GroceryStore(T product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public int orderQuantity() {
        return quantity;
    }

    @Override
    public Market<T> setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public T getProduct() {
        return product;
    }

    @Override
    public BigInteger getQuantity() {
        return BigInteger.valueOf(quantity);
    }
}
