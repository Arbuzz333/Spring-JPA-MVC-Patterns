package avakhidov.factories.bakery;

import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.KindFlour;

import java.math.BigDecimal;

public interface StorageBakery<T extends Ingredient> {

    BigDecimal stockValue();

    KindFlour getKindFlour();
}
