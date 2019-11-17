package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.OvenWorks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.RoundingMode;

public class BakeryConditionBun<T extends Product<ParameterPrepareDough>, S extends Ingredient> implements BakeryCondition<T, S> {

    private static final Logger logger = LoggerFactory.getLogger(BakeryConditionBun.class);

    private OvenWorks<T> oven;

    private Market<T> market;

    private StorageBakery<S> storageBakery;

    private BakeryConditionEnum bakeryCondition = BakeryConditionEnum.DOWNTIME;

    public BakeryConditionBun(OvenWorks<T> oven, Market<T> market, StorageBakery<S> bakery) {
        this.oven = oven;
        this.market = market;
        this.storageBakery = bakery;
    }

    @Override
    public void updateOven(OvenWorks<T> oven) {
        this.oven = oven;
        updateCondition();
    }

    @Override
    public void updateMarket(Market<T> market) {
        this.market = market;
        updateCondition();
    }

    @Override
    public void updateStorageBakery(StorageBakery<S> storageBakery) {
        this.storageBakery = storageBakery;
        updateCondition();
    }

    public BakeryConditionEnum getCondition() {
        return this.bakeryCondition;
    }

    @Override
    public BakeryConditionEnum updateCondition() {

        logger.info("market.orderQuantity() {}", market.orderQuantity());
        logger.info("oven.getMinPartyProduct() {}", oven.getMinPartyProduct());
        logger.info("oven.getMinPartyIngredient() {}", oven.getMinPartyIngredient());
        logger.info("oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP) {}", oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP));
        logger.info("storageBakery.stockValue() {}", storageBakery.stockValue());
        logger.info("storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP) {}", storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP));
        logger.info("oven.getParams().getOvenSituation() {}", oven.getParams().getOvenSituation());

        if (market.orderQuantity() > oven.getMinPartyProduct() &&
                oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation().equals(Oven.OvenSituation.HOLD)) {

            this.bakeryCondition = BakeryConditionEnum.PREPARATION_FOR_WORK;
            return bakeryCondition;

        } else if (market.orderQuantity() > oven.getMinPartyProduct() &&
                oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation() == Oven.OvenSituation.HOT) {

            this.bakeryCondition = BakeryConditionEnum.WORKS;
            return bakeryCondition;

        } else {
            this.bakeryCondition = BakeryConditionEnum.DOWNTIME;
            return bakeryCondition;
        }

    }

}
