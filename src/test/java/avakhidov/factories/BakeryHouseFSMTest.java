package avakhidov.factories;


import avakhidov.factories.bakery.BakeryHouseFSM;
import avakhidov.factories.bakery.BakeryHouseFSMSingletonEnum;
import avakhidov.factories.bakery.StorageBakery;
import avakhidov.factories.bakery.StorageBakeryFlour;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.BunShop;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.OvenWorks;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.OvenWorksImpl;
import avakhidov.factories.service.serviceimpl.PreheatedOven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BakeryHouseFSMTest {

    @Autowired
    BakeryHouseFSM<Bun, Flour> bakeryHouseFSM;

    @Autowired
    CornBunRecipe  cornBunRecipe;

    @Test
    public void downtimeStateTest() {

        Oven<Bun> oven = new PreheatedOven<>();
        OvenWorks<Bun> ovenWorks = new OvenWorksImpl<>(oven,
                cornBunRecipe.cooked(0.15));
        Market<Bun> market = new BunShop(7);
        StorageBakery<Flour> storageBakery = new StorageBakeryFlour(
                new BigDecimal(525), new BuckwheatFlour(GrindingFlour.MEDIUM));

        bakeryHouseFSM.setOven(ovenWorks);
        bakeryHouseFSM.setMarket(market);
        bakeryHouseFSM.setStorageBakery(storageBakery);

        BakeryConditionEnum bakeryCondition = bakeryHouseFSM.getCurrentState();
        assertEquals(bakeryCondition, BakeryConditionEnum.DOWNTIME);

        bakeryCondition = bakeryHouseFSM.downtimeState();
        assertEquals(bakeryCondition, BakeryConditionEnum.PREPARATION_FOR_WORK);

        bakeryCondition = bakeryHouseFSM.preparationForWorkState();
        assertEquals(bakeryCondition, BakeryConditionEnum.WORKS);

        bakeryCondition = bakeryHouseFSM.workState();
        assertEquals(bakeryCondition, BakeryConditionEnum.DOWNTIME);
    }

    @Test
    public void BakeryHouseFSMSingletonEnumTest() {
        BakeryHouseFSM<Bun, Flour> bakeryHouseFSM = BakeryHouseFSMSingletonEnum.INSTANCE.getBakeryHouseFSM();

        BakeryConditionEnum bakeryCondition = bakeryHouseFSM.getCurrentState();
        assertEquals(bakeryCondition, BakeryConditionEnum.DOWNTIME);
    }

}
