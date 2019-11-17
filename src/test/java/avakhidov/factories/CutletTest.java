package avakhidov.factories;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.cutlet.*;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.cutlet.cutletimpl.CutletServiceImpl;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletTest {

    @Autowired
    private MeatServiceImpl meatService;

    @Autowired
    private VealCutletRecipe vealCutletRecipe;

    @Autowired
    private PorkCutletRecipe porkCutletRecipe;

    @Autowired
    CutletServiceImpl cutletService;

    private static final Logger logger = Logger.getLogger(CutletTest.class);

    @Test
    public void cutletTest() {
        final Cutlet porkCutlet = porkCutletRecipe.cooked(185, LocalTime.of(0, 55), 100);

        final Cutlet chickenCutlet = new ChickenCutlet(
                new ChickenMeat(FatMeat.DIETARY, new Chicken())
                , true
                , 120.0);

        Cutlet.SesameBun sesameBun = porkCutlet.createSesameBun(ParameterDoughEnum.WHEAT_FLOUR_FINE
                , true
                , new Sesame()
                , 180
                , LocalTime.of(9, 30)
                , 80.0);

        sesameBun.setKindDough();

        List<Cutlet> cutlets = new ArrayList<>();
        cutlets.add(porkCutlet);
        cutlets.add(chickenCutlet);

        final Cutlet porkCutletFat = new PorkCutlet(
                new PorkMeat(meatService.getMoreFatInMeat(porkCutlet.getMeat().getFatMeat()), new Pig())
                , true
                , 120);

        final Cutlet muttomCutlet = new MuttonCutlet(
                new MuttonMeat(meatService.getLessFatInMeat(porkCutlet.getMeat().getFatMeat()), new Sheep())
                , true
                , 110);
        final Cutlet vealCutlet = vealCutletRecipe.cooked(175, LocalTime.of(0, 40), 90);

        cutlets.add(porkCutletFat);
        cutlets.add(muttomCutlet);
        cutlets.add(vealCutlet);

        assertEquals(porkCutletFat.getMeat().getFatMeat(), FatMeat.SPECK);
        assertEquals(muttomCutlet.getMeat().getFatMeat(), FatMeat.LOWFAT);
        assertEquals(vealCutlet.getMeat().getFatMeat(), FatMeat.DIETARY);
        assertEquals(5, cutlets.size());
        assertEquals(KindDough.YEAST_DOUGH, porkCutlet.getParameterPrepareDoughBun().getKindDough());

    }

    @Test
    public void cutletServiceTest() {

        final Cutlet vealCutlet = vealCutletRecipe.cooked(175, LocalTime.of(0, 40), 90);
        final Cutlet porkCutlet = porkCutletRecipe.cooked(185, LocalTime.of(0, 55), 100);

        Cutlet cutlet = cutletService.getMoreFatCutlet(vealCutlet, porkCutlet);

        assertFalse(cutlet instanceof VealCutlet);

    }

}
