package avakhidov.factories;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
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
                cornBunRecipe.cooked(33, LocalTime.of(0, 23)),
                wheatBunRecipe.cooked(35, LocalTime.of(14, 25)),
                buckwheatBunRecipe.cooked(37, LocalTime.of(0, 27)));

        assertEquals(buns.size(), 3);
        assertEquals(buns.get(0).getPrepack().getFlour().getKind(), KindFlour.CORN);
        assertEquals(buns.get(1).getPrepack().getFlour().getKind(), KindFlour.WHEAT);
        assertEquals(buns.get(2).getPrepack().getFlour().getKind(), KindFlour.BUCKWHEAT);

        assertEquals(buns.get(0).getPrepack().getFlour().getGrinding(), GrindingFlour.COARSE);
        assertEquals(buns.get(1).getPrepack().getFlour().getGrinding(), GrindingFlour.FINE);
        assertEquals(buns.get(2).getPrepack().getFlour().getGrinding(), GrindingFlour.MEDIUM);

        buns.forEach(Bun::setKindDough);

        assertEquals(buns.get(0).getPrepack().getKindDough(), KindDough.SHORTCRUST_PASTRY);
        assertEquals(buns.get(1).getPrepack().getKindDough(), KindDough.YEAST_DOUGH);
        assertEquals(buns.get(2).getPrepack().getKindDough(), KindDough.PUFF_PASTRY);

        buns.forEach(r -> {logger.info(r.getPrepack().getKindDough());
            logger.info(r.getPrepack().getFlour().getGrinding());
        });

    }
}
