package avakhidov.factories.bakery;

import avakhidov.factories.entity.ingredient.Ingredient;

import java.math.BigDecimal;

public interface StorageBakery<T extends Ingredient> {

    BigDecimal stockValue(T stock);
}
