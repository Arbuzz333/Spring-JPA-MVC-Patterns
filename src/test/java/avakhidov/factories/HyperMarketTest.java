package avakhidov.factories;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.MuttonCutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.market.BunShop;
import avakhidov.factories.market.GroceryStore;
import avakhidov.factories.market.HyperMarket;
import avakhidov.factories.market.Market;
import avakhidov.factories.market.SuperMarket;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HyperMarketTest {

    @Autowired
    ChickenCutletRecipe chickenCutletRecipe;

    @Autowired
    VealCutletRecipe vealCutletRecipe;

    @Autowired
    WheatBunRecipe wheatBunRecipe;
    @Autowired
    BuckwheatBunRecipe buckwheatBunRecipe;
    @Autowired
    CornBunRecipe cornBunRecipe;

    @Autowired
    HyperMarket<Cutlet> hyperMarket;
    @Autowired
    SuperMarket<CornBun> superMarketCornBun;
    @Autowired
    SuperMarket<WheatBun> superMarketWheatBun;

    @Before
    public void createHyperMarket() {
        hyperMarket.setQuantity(2050);

        GroceryStore<ChickenCutlet> chickenCutletGroceryStore =
                new GroceryStore<>((ChickenCutlet) chickenCutletRecipe.cooked(0.125), 125);
        GroceryStore<MuttonCutlet> muttonCutletGroceryStore =
                new GroceryStore<>(MuttonCutlet.builderMuttonCutlet()
                        .withRecipeReady(true)
                        .withFinished(Finished.RAW)
                        .withMainIngredient(new MuttonMeat(FatMeat.MEDIUMFAT, new Sheep()))
                        .withWeight(0.15)
                        .build(),
                        220);
        GroceryStore<VealCutlet> vealCutletGroceryStore =
                new GroceryStore<>((VealCutlet) vealCutletRecipe.cooked(0.16), 310);

        superMarketCornBun.setMarket(chickenCutletGroceryStore, muttonCutletGroceryStore, vealCutletGroceryStore);
        superMarketCornBun.setProduct(CornBun.builderCornBun()
                .withFinished(Finished.RAW)
                .withRecipeReady(true)
                .withMainIngredient(ParameterDoughEnum.CORN_FLOUR_COARSE.toKneadTheDough())
                .withWeight(0.135)
                .build());
        superMarketCornBun.setQuantity(555);

        BunShop wheatBunShop = new BunShop(25, wheatBunRecipe.cooked(0.09));
        GroceryStore<BuckwheatBun> buckwheatBunGroceryStore =
                new GroceryStore<>((BuckwheatBun) buckwheatBunRecipe.cooked(0.11), 75);
        GroceryStore<CornBun> cornBunGroceryStore = new GroceryStore<>((CornBun) cornBunRecipe.cooked(0.085), 285);
        superMarketWheatBun.setMarket(wheatBunShop, buckwheatBunGroceryStore, cornBunGroceryStore);

        WheatBun wheatBun = (WheatBun) wheatBunRecipe.cooked(0.075);
        superMarketWheatBun.setProduct(wheatBun);
        superMarketWheatBun.setQuantity(500);

        hyperMarket.getSuperMarkets().add(superMarketCornBun);
        hyperMarket.getSuperMarkets().add(superMarketWheatBun);

        Cutlet<MuttonMeat> cookedMuttonCutlet = MuttonCutlet.cooked(0.215);
        hyperMarket.setProduct(cookedMuttonCutlet);
    }

    @Test
    public void hyperMarketTest() {
        assertEquals(hyperMarket.orderQuantity(), 2495);

        hyperMarket.reduceQuantity(80);
        assertEquals(hyperMarket.orderQuantity(), 3343);

        Market market = (Market) hyperMarket.getSuperMarkets().get(0).getMarketList().get(0);
        assertEquals(market.getQuantity().intValue(), 25);
        Market market2 = (Market) hyperMarket.getSuperMarkets().get(1).getMarketList().get(0);
        assertEquals(market2.getQuantity().intValue(), 5);

        assertEquals(hyperMarket.getQuantity().intValue(), 410);

        GroceryStore<WheatBun> wheatBunGroceryStore =
                new GroceryStore<>((WheatBun) wheatBunRecipe.cooked(0.05), 200);

        List<Market> markets = hyperMarket.setMarket(wheatBunGroceryStore);
        assertEquals(markets.size(), 7);
        assertEquals(hyperMarket.getSuperMarkets().get(1).getMarketList().size(), 4);
        assertEquals(hyperMarket.orderQuantity(), 3543);
    }
}

