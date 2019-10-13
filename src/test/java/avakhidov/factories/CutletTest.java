package avakhidov.factories;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.cutlet.*;
import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletTest {

    @Autowired
    private MeatServiceImpl meatService;

    private static final Logger logger = Logger.getLogger(CutletTest.class);

    @Test
    public void cutletTest() {
        final Cutlet porkCutlet = new PorkCutlet(
                new PorkMeat(FatMeat.MEDIUMFAT, new Pig())
                , true
                , 100.0);
        final Cutlet chickenCutlet = new ChickenCutlet(
                new ChickenMeat(FatMeat.DIETARY, new Chicken())
                , true
                , 120.0);

        Cutlet.SesameBun sesameBun = porkCutlet.createSesameBun(ParameterDoughEnum.WHEAT_FLOUR_FINE
                , true
                , new Sesame()
                , 180
                , LocalTime.of(9, 30));

        List<Cutlet> cutlets = new ArrayList<>();
        cutlets.add(porkCutlet);
        cutlets.add(chickenCutlet);

        Cutlet porkCutletFat = new PorkCutlet(
                new PorkMeat(meatService.getMoreFatInMeat(porkCutlet.getMeat().getFatMeat()), new Pig())
                , true
                , 120);

        Cutlet muttomCutlet = new MuttonCutlet(
                new MuttonMeat(meatService.getLessFatInMeat(porkCutlet.getMeat().getFatMeat()), new Sheep())
                , true
                , 110);
        Cutlet vealCutlet = new VealCutlet(
                new VealMeat(meatService.getLessFatInMeat(chickenCutlet.getMeat().getFatMeat()), new Calf())
                , true
                , 90);

        cutlets.add(porkCutletFat);
        cutlets.add(muttomCutlet);

        assertEquals(porkCutletFat.getMeat().getFatMeat(), FatMeat.SPECK);
        assertEquals(muttomCutlet.getMeat().getFatMeat(), FatMeat.LOWFAT);
        assertEquals(vealCutlet.getMeat().getFatMeat(), FatMeat.DIETARY);
    }

}
