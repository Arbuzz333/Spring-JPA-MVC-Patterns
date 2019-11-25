package avakhidov.factories.bakery;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.OvenWorks;
import avakhidov.factories.service.serviceimpl.PreheatedOven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayDeque;

@Component
public class BakeryHouseFSM<T extends Product<ParameterPrepareDough>, S extends Ingredient> {

    private final Logger logger = LoggerFactory.getLogger(BakeryHouseFSM.class);

    private ArrayDeque<BakeryConditionEnum> states = new ArrayDeque<>();

    private Market<T> market;

    private OvenWorks<T> oven;

    private StorageBakery<S> storageBakery;

    private int order = 0;

    {
        this.states.add(BakeryConditionEnum.DOWNTIME);
    }

    public BakeryConditionEnum downtimeState() {
        if (!this.states.peekLast().equals(BakeryConditionEnum.DOWNTIME)) {
            return this.states.peekLast();
        }

        logger.info("market.orderQuantity() {}", market.orderQuantity());
        logger.info("oven.getMinPartyProduct() {}", oven.getMinPartyProduct());
        logger.info("oven.getMinPartyIngredient().setScale(3, RoundingMode.HALF_UP) {}", oven.getMinPartyIngredient(market.orderQuantity()).setScale(3, RoundingMode.HALF_UP));
        logger.info("storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP) {}", storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP));

        if(order > oven.getMinPartyProduct() &&
                oven.getMinPartyIngredient(market.orderQuantity()).setScale(3, RoundingMode.HALF_UP)
                        .compareTo(storageBakery.stockValue().setScale(3, RoundingMode.HALF_UP)) < 0) {

            storageBakery.setWeight(
                    storageBakery.stockValue()
                            .subtract(oven.getMinPartyIngredient(order),
                                    new MathContext(3, RoundingMode.HALF_UP)));
            oven.setOven(new PreheatedOven<>());
            states.pollLast();
            states.addLast(BakeryConditionEnum.PREPARATION_FOR_WORK);
        }
        logger.info("storageBakery.stockValue() {}", storageBakery.stockValue());
        return this.states.peekLast();
    }

    public BakeryConditionEnum preparationForWorkState() {
        if (!this.states.peekLast().equals(BakeryConditionEnum.PREPARATION_FOR_WORK)) {
            return this.states.peekLast();
        }
        logger.info("order {}", order);

        if (order > 0) {
            order = 0;
            states.pollLast();
            states.addLast(BakeryConditionEnum.WORKS);
        }
        return this.states.peekLast();
    }

    public BakeryConditionEnum workState() {
        if (!this.states.peekLast().equals(BakeryConditionEnum.WORKS)) {
            return this.states.peekLast();
        }

        logger.info("order {}", order);

        if (order <= 0) {
            states.pollLast();
            states.addLast(BakeryConditionEnum.DOWNTIME);
        }
        return this.states.peekLast();
    }


    public BakeryConditionEnum getCurrentState() {
        return states.peekLast();
    }


    public void setMarket(Market<T> market) {
        order = market.orderQuantity();
        this.market = market;
    }

    public void setOven(OvenWorks<T> oven) {
        this.oven = oven;
    }

    public void setStorageBakery(StorageBakery<S> storageBakery) {
        this.storageBakery = storageBakery;
    }

}
