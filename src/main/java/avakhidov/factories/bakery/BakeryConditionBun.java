package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.BunShop;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.serviceimpl.OvenWorks;
import lombok.Setter;

import java.math.RoundingMode;

@Setter
public class BakeryConditionBun<T extends Product, S extends Ingredient> implements BakeryCondition {

    private Oven<T> oven;

    private Market market;

    private StorageBakery<S> storageBakery;

    public BakeryConditionBun(Oven<T> oven, Market market, StorageBakery<S> bakery) {
        this.oven = oven;
        this.market = market;
        this.storageBakery = bakery;
    }

    @Override
    public BakeryConditionEnum getCondition() {

        if (market.orderQuantity() != BunShop.MAX_QUANTITY &&
                market.orderQuantity() > OvenWorks.MIN_PARTY &&
                OvenWorks.MIN_FLOUR.setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().containsKey(Oven.OvenSituation.HOLD)) {
            return BakeryConditionEnum.PREPARATION_FOR_WORK;
        } else if (market.orderQuantity() != BunShop.MAX_QUANTITY &&
                market.orderQuantity() > OvenWorks.MIN_PARTY &&
                OvenWorks.MIN_FLOUR.setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().containsKey(Oven.OvenSituation.HOT)) {
            return BakeryConditionEnum.WORKS;
        } else {
            return BakeryConditionEnum.DOWNTIME;
        }

    }

}
