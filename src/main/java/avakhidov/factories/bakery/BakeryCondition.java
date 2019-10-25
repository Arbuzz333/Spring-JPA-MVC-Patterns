package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.service.Oven;
import avakhidov.factories.market.Market;

public interface BakeryCondition<T extends Product, S extends Ingredient> {

    void update(Oven<T> oven, Market market, StorageBakery<S> bakery);

}
