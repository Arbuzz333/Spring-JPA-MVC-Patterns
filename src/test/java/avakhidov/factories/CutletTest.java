package avakhidov.factories;

import avakhidov.factories.entity.Sesame;
import avakhidov.factories.entity.WheatFlour;
import avakhidov.factories.entity.cutlet.*;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.GrindingFlour;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletTest {

    @Test
    public void cutletTest() {
        PorkCutlet porkCutlet = new PorkCutlet(
                new PorkMeat(FatMeat.MEDIUMFAT, new Pig())
                , true
                , 100.0);
        ChickenCutlet chickenCutlet = new ChickenCutlet(
                new ChickenMeat(FatMeat.DIETARY, new Chicken())
                , true
                , 120.0);

        Cutlet.SesameBun sesame = porkCutlet.new SesameBun(new WheatFlour(GrindingFlour.FINE)
                , true
                , new Sesame());
    }

}
