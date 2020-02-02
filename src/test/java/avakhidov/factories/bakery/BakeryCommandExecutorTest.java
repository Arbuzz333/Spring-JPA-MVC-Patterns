package avakhidov.factories.bakery;

import avakhidov.factories.comand.CommandBakeryHouseDowntime;
import avakhidov.factories.comand.CommandBakeryHousePreparationForWork;
import avakhidov.factories.comand.CommandBakeryHouseWork;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.bakery.BakeryConditionEnum;
import avakhidov.factories.market.BunShop;
import avakhidov.factories.market.Market;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.OvenWorks;
import avakhidov.factories.service.serviceimpl.OvenWorksImpl;
import avakhidov.factories.service.serviceimpl.PreheatedOven;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BakeryCommandExecutorTest {

    @Autowired
    BakeryHouseFSM<Bun, Flour> bakeryHouseFSM;

    @Autowired
    private HistoryBakeryHouse history;

    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Before
    public void fillBakery() {
        Oven<Bun> oven = new PreheatedOven<>();
        OvenWorks<Bun> ovenWorks = new OvenWorksImpl<>(oven,
                wheatBunRecipe.cooked(0.15));
        Market<Bun> market = new BunShop(7);
        StorageBakery<Flour> storageBakery = new StorageBakeryFlour(
                new BigDecimal(525), new WheatFlour(GrindingFlour.FINE));

        bakeryHouseFSM.setOven(ovenWorks);
        bakeryHouseFSM.setMarket(market);
        bakeryHouseFSM.setStorageBakery(storageBakery);
    }

    @Test
    public void commandExecutorTest() {
        BakeryCommandExecutor executor = new BakeryCommandExecutor(bakeryHouseFSM, history);
        executor.execute(new CommandBakeryHouseDowntime(bakeryHouseFSM));
        executor.execute(new CommandBakeryHousePreparationForWork(bakeryHouseFSM));
        executor.execute(new CommandBakeryHouseWork(bakeryHouseFSM));

        assertEquals(bakeryHouseFSM.getCurrentState(), BakeryConditionEnum.DOWNTIME);

        assertTrue(executor.undoExecute());
        BakeryConditionEnum currentState = bakeryHouseFSM.getCurrentState();
        assertEquals(currentState, BakeryConditionEnum.WORKS);

        assertTrue(executor.redoExecute());
        assertEquals(bakeryHouseFSM.getCurrentState(), BakeryConditionEnum.WORKS);
    }

    @Test
    public void commandExecutorTest2() {
        BakeryCommandExecutor executor = new BakeryCommandExecutor(bakeryHouseFSM, history);
        executor.execute(new CommandBakeryHouseDowntime(bakeryHouseFSM));
        executor.execute(new CommandBakeryHousePreparationForWork(bakeryHouseFSM));

        assertEquals(bakeryHouseFSM.getCurrentState(), BakeryConditionEnum.WORKS);

        assertTrue(executor.undoExecute());
        BakeryConditionEnum currentState = bakeryHouseFSM.getCurrentState();
        assertEquals(currentState, BakeryConditionEnum.PREPARATION_FOR_WORK);

        assertTrue(executor.redoExecute());
        assertEquals(bakeryHouseFSM.getCurrentState(), BakeryConditionEnum.PREPARATION_FOR_WORK);

        executor.execute(new CommandBakeryHousePreparationForWork(bakeryHouseFSM));
        assertEquals(bakeryHouseFSM.getCurrentState(), BakeryConditionEnum.WORKS);
    }


}
