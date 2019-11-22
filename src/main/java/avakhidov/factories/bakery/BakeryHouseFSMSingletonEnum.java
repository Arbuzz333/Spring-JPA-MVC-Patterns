package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Ingredient;

public enum BakeryHouseFSMSingletonEnum {

    INSTANCE;

    public <T extends Product<ParameterPrepareDough>, S extends Ingredient> BakeryHouseFSM<T, S> getBakeryHouseFSM() {
        return new BakeryHouseFSM<>();
    }

}
