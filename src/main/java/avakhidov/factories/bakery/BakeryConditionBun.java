package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.serviceimpl.OvenWorks;

import java.math.RoundingMode;

public class BakeryConditionBun<T extends Product, S extends Ingredient> implements BakeryCondition {

    private OvenWorks oven;

    private Market market;

    private StorageBakery<S> storageBakery;

    public BakeryConditionBun(OvenWorks oven, Market<T> market, StorageBakery<S> bakery) {
        this.oven = oven;
        this.market = market;
        this.storageBakery = bakery;
    }

    public void updateOven(OvenWorks oven) {
        this.oven = oven;
    }

    public void updateMarket(Market<T> market) {
        this.market = market;
    }

    public void updateStorageBakery(StorageBakery<S> storageBakery) {
        this.storageBakery = storageBakery;
    }

    

    @Override
    public BakeryConditionEnum getCondition() {

        if (market.orderQuantity() != market.getMaxQuantity() &&
                market.orderQuantity() > oven.getMinPartyBun() &&
                oven.getMinPartyFlour().setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation().equals(Oven.OvenSituation.HOLD)) {
            return BakeryConditionEnum.PREPARATION_FOR_WORK;
        } else if (market.orderQuantity() != market.getMaxQuantity() &&
                market.orderQuantity() > oven.getMinPartyBun() &&
                oven.getMinPartyFlour().setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation().equals(Oven.OvenSituation.HOT)) {
            return BakeryConditionEnum.WORKS;
        } else {
            return BakeryConditionEnum.DOWNTIME;
        }

    }

}
