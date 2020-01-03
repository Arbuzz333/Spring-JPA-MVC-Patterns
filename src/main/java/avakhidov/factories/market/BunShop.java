package avakhidov.factories.market;

import avakhidov.factories.entity.bun.Bun;

import java.math.BigInteger;

public class BunShop implements Market<Bun> {

    private static final int MAX_QUANTITY = 55;

    private int quantity;

    private Bun bun;

    public BunShop(int quantity) {
        this.quantity = quantity;
    }

    public BunShop(int quantity, Bun bun) {
        this.quantity = quantity;
        this.bun = bun;
    }

    @Override
    public int orderQuantity() {
        return quantity >= MAX_QUANTITY ? 0 : (MAX_QUANTITY - quantity);
    }

    @Override
    public BunShop setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public Bun getProduct() {
        return bun;
    }

    @Override
    public BigInteger getQuantity() {
        return BigInteger.valueOf(quantity);
    }

}
