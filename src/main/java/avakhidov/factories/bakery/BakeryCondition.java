package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.BakeryConditionEnum;

public interface BakeryCondition<T extends Product, S extends Ingredient> {

    BakeryConditionEnum getCondition();

}
