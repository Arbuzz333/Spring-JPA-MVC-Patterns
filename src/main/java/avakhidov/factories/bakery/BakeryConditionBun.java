package avakhidov.factories.bakery;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.BakeryConditionEnum;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import lombok.Setter;

@Setter
public class BakeryConditionBun implements BakeryCondition<Bun, Flour> {

    private Oven<Bun> oven;

    private Market market;

    private StorageBakery<Flour> storageBakery;

    public BakeryConditionBun(Oven<Bun> oven, Market market, StorageBakery<Flour> bakery) {
        this.oven = oven;
        this.market = market;
        this.storageBakery = bakery;
    }

    @Override
    public BakeryConditionEnum getCondition() {
        return BakeryConditionEnum.DOWNTIME;
    }

}
