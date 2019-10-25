package avakhidov.factories.bakery;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;

public class BakeryConditionBun implements BakeryCondition<Bun, Flour> {

    private Oven<Bun> oven;

    private Market market;

    private StorageBakery<Flour> storageBakery;

    @Override
    public void update(Oven<Bun> oven, Market market, StorageBakery<Flour> bakery) {

    }
}
