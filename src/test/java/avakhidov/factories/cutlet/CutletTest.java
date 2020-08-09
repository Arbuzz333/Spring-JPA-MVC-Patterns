package avakhidov.factories.cutlet;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.cutlet.*;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.cutlet.cutletimpl.CutletServiceImpl;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import avakhidov.factories.service.cutlet.cutletimpl.PorkCutletRecipe;
import avakhidov.factories.service.cutlet.cutletimpl.VealCutletRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        final Cutlet<PorkMeat> porkCutlet = porkCutletRecipe.cooked(0.100);

        final Cutlet<ChickenMeat> chickenCutlet = new ChickenCutlet(
                (ChickenMeat) meatService.buildMeat(ChickenMeat.class)
                , true
                , 120.0);

        Cutlet<PorkMeat>.SesameBun sesameBun = porkCutlet.createSesameBun(ParameterDoughEnum.WHEAT_FLOUR_FINE
                , true
                , new Sesame()
                , 80.0);

        sesameBun.setKindDough();

        List<Cutlet<? extends Meat>> cutlets = new ArrayList<>();
        cutlets.add(porkCutlet);
        cutlets.add(chickenCutlet);

        final Cutlet<? extends Meat> porkCutletFat = new PorkCutlet(
                new PorkMeat(meatService.getMoreFatInMeat(porkCutlet.getMainIngredient().getFatMeat()), new Pig())
                , true
                , 120);

        final Cutlet<? extends Meat> muttonCutlet = new MuttonCutlet(
                new MuttonMeat(meatService.getLessFatInMeat(porkCutlet.getMainIngredient().getFatMeat()), new Sheep())
                , true
                , 110);
        final Cutlet<? extends Meat> vealCutlet = vealCutletRecipe.cooked(90);

        cutlets.add(porkCutletFat);
        cutlets.add(muttonCutlet);
        cutlets.add(vealCutlet);

        assertEquals(porkCutletFat.getMainIngredient().getFatMeat(), FatMeat.SPECK);
        assertEquals(muttonCutlet.getMainIngredient().getFatMeat(), FatMeat.MEDIUM_FAT);
        assertEquals(vealCutlet.getMainIngredient().getFatMeat(), FatMeat.LOW_FAT);
        assertEquals(5, cutlets.size());
        assertEquals(KindDough.YEAST_DOUGH, porkCutlet.getParameterPrepareDoughBun().getKindDough());

    }

    @Test
    public void cutletServiceTest() {

        final Cutlet<VealMeat> vealCutlet = vealCutletRecipe.cooked(0.90);
        final Cutlet<PorkMeat> porkCutlet = porkCutletRecipe.cooked(0.100);

        Cutlet<?> cutlet = cutletService.getMoreFatCutlet(vealCutlet, porkCutlet);

        assertFalse(cutlet instanceof VealCutlet);

    }

}
