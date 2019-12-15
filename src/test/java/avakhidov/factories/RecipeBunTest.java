package avakhidov.factories;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeBunTest {

    private static final Logger logger = Logger.getLogger(RecipeBunTest.class);


    @Autowired
    private CornBunRecipe cornBunRecipe;

    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Autowired
    private BuckwheatBunRecipe buckwheatBunRecipe;

    @Test
    public void cookedBunTest() {

        List<Bun> buns = Arrays.asList(
                cornBunRecipe.cooked(0.75),
                wheatBunRecipe.cooked(0.120),
                buckwheatBunRecipe.cooked(0.95));

        assertEquals(buns.size(), 3);
        assertEquals(buns.get(0).getMainIngredient().getFlour().getKind(), KindFlour.CORN);
        assertEquals(buns.get(1).getMainIngredient().getFlour().getKind(), KindFlour.WHEAT);
        assertEquals(buns.get(2).getMainIngredient().getFlour().getKind(), KindFlour.BUCKWHEAT);

        assertEquals(buns.get(0).getMainIngredient().getFlour().getGrinding(), GrindingFlour.COARSE);
        assertEquals(buns.get(1).getMainIngredient().getFlour().getGrinding(), GrindingFlour.FINE);
        assertEquals(buns.get(2).getMainIngredient().getFlour().getGrinding(), GrindingFlour.MEDIUM);

        WheatBun wheatBun = (WheatBun) buns.get(1);
        wheatBun.setLocalTime(LocalTime.of(15, 5));
        buns.forEach(Bun::setKindDough);

        assertEquals(buns.get(0).getMainIngredient().getKindDough(), KindDough.SHORTCRUST_PASTRY);
        assertEquals(buns.get(1).getMainIngredient().getKindDough(), KindDough.YEAST_DOUGH);
        assertEquals(buns.get(2).getMainIngredient().getKindDough(), KindDough.PUFF_PASTRY);

        buns.forEach(r -> {logger.info(r.getMainIngredient().getKindDough());
            logger.info(r.getMainIngredient().getFlour().getGrinding());
        });

    }

    @Test
    public void buildAndCookedCornBunTest() {
        Bun cornBun = cornBunRecipe.cooked(0.75);

        CornBun cornBunBuild = CornBun.builderCornBun()
                .withRecipeReady(true)
                .withFinished(Finished.RAW)
                .withMainIngredient(ParameterDoughEnum.CORN_FLOUR_COARSE.toKneadTheDough())
                .withWeight(0.75)
                .build();

        assertEquals(cornBun, cornBunBuild);
    }
}
