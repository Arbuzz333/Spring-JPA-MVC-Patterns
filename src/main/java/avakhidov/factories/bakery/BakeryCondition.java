package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.OvenWorks;

public interface BakeryCondition<T extends Product, S extends Ingredient> {

    void updateOven(OvenWorks<T> oven);

    void updateMarket(Market<T> market);

    void updateStorageBakery(StorageBakery<S> storageBakery);

    BakeryConditionEnum getCondition();

    BakeryConditionEnum updateCondition();

}
