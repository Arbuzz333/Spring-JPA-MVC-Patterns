package avakhidov.factories.entity.ingredient;

public class Egg extends Ingredient {

    private String category;

    private int quantity;

    public Egg(String category, int quantity) {
        this.category = category;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
}
