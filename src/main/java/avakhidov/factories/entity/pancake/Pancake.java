package avakhidov.factories.entity.pancake;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.service.MainIngredient;

public abstract class Pancake<T extends Flour> extends Product<PancakePrepareDough<T>> {

    private MainIngredient ingredient;

    Pancake(PancakePrepareDough<T> mainIngredient, double weight, MainIngredient ingredient) {
        super(mainIngredient, weight);
        this.ingredient = ingredient;
    }

    public MainIngredient getIngredient() {
        return ingredient;
    }
}
