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

    private BakeryHouseFSM<T, S> bakeryHouseFSM = BakeryHouseFSMSingletonEnum.INSTANCE.getBakeryHouseFSM();

    private BakeryConditionEnum bakeryCondition = BakeryConditionEnum.DOWNTIME;

    public BakeryConditionBun(OvenWorks<T> oven, Market<T> market, StorageBakery<S> bakery) {
        this.oven = oven;
        this.market = market;
        this.storageBakery = bakery;
        bakeryHouseFSM.setOven(oven);
        bakeryHouseFSM.setMarket(market);
        bakeryHouseFSM.setStorageBakery(bakery);
    }

    @Override
    public void updateOven(OvenWorks<T> oven) {
        this.oven = oven;
        this.bakeryHouseFSM.setOven(oven);
        updateCondition();
    }

    @Override
    public void updateMarket(Market<T> market) {
        this.market = market;
        this.bakeryHouseFSM.setMarket(market);
        updateCondition();
    }

    @Override
    public void updateStorageBakery(StorageBakery<S> storageBakery) {
        this.storageBakery = storageBakery;
        this.bakeryHouseFSM.setStorageBakery(storageBakery);
        updateCondition();
    }

    public BakeryConditionEnum getCondition() {
        return this.bakeryCondition;
    }

    @Override
    public BakeryConditionEnum updateCondition() {

        logger.info("market.orderQuantity() {}", market.orderQuantity());
        logger.info("oven.getMinPartyProduct() {}", oven.getMinPartyProduct());
        logger.info("oven.getMinPartyIngredient() {}", oven.getMinPartyIngredient(market.orderQuantity()));
        logger.info("oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP) {}", oven.getMinPartyIngredient(market.orderQuantity()).setScale(3, RoundingMode.HALF_UP));
        logger.info("storageBakery.stockValue() {}", storageBakery.stockValue());
        logger.info("storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP) {}", storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP));
        logger.info("oven.getParams().getOvenSituation() {}", oven.getParams().getOvenSituation());

        if (market.orderQuantity() > oven.getMinPartyProduct() &&
                oven.getMinPartyIngredient(market.orderQuantity()).setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation().equals(Oven.OvenSituation.HOLD)) {

            this.bakeryCondition = BakeryConditionEnum.PREPARATION_FOR_WORK;
            bakeryHouseFSM.downtimeState();
            logger.info("current State {}", bakeryHouseFSM.getCurrentState());
            return bakeryCondition;

        } else if (market.orderQuantity() > oven.getMinPartyProduct() &&
                oven.getMinPartyIngredient(market.orderQuantity()).setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0 &&
                oven.getParams().getOvenSituation() == Oven.OvenSituation.HOT) {

            this.bakeryCondition = BakeryConditionEnum.WORKS;
            bakeryHouseFSM.preparationForWorkState();
            logger.info("current State {}", bakeryHouseFSM.getCurrentState());
            return bakeryCondition;

        } else {
            this.bakeryCondition = BakeryConditionEnum.DOWNTIME;
            bakeryHouseFSM.workState();
            logger.info("current State {}", bakeryHouseFSM.getCurrentState());
            return bakeryCondition;
        }

    }

}
