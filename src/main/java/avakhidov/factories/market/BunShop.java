package avakhidov.factories.market;

import avakhidov.factories.entity.bun.Bun;

public class BunShop implements Market<Bun> {

    private static final int MAX_QUANTITY = 25;

    private int quantity;

    public BunShop(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int orderQuantity() {
        return quantity >= MAX_QUANTITY ? 0 : (MAX_QUANTITY - quantity);
    }

    @Override
    public int getMaxQuantity() {
        return MAX_QUANTITY;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
