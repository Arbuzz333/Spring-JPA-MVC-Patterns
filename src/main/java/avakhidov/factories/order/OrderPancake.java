package avakhidov.factories.order;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.entity.pancake.Pancake;

public class OrderPancake<T extends Flour> {

    private Pancake<T>  pancake;

    private Ingredient ingredient;

    public OrderPancake(Pancake<T> pancake, Ingredient ingredient) {
        this.pancake = pancake;
        this.ingredient = ingredient;
    }

    public Pancake<T> getPancake() {
        return pancake;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
