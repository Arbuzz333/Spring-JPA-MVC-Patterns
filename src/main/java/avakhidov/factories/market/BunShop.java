package avakhidov.factories.market;

import avakhidov.factories.entity.bun.Bun;

public class BunShop implements Market<Bun> {

    public static final int MAX_QUANTITY = 25;

    private int quantity;

    public BunShop(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int orderQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
