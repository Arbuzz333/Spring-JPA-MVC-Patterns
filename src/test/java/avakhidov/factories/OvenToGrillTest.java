package avakhidov.factories;


import avakhidov.factories.adapters.OvenToGrill;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.grill.Grill;
import avakhidov.factories.grill.energy.Energy;
import avakhidov.factories.grill.energy.energyimpl.CharCoal;
import avakhidov.factories.grill.energy.energyimpl.Gas;
import avakhidov.factories.grill.grillimpl.GrillCharCoal;
import avakhidov.factories.grill.grillimpl.GrillGas;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OvenToGrillTest {

    @Autowired
    OvenToGrill<Cutlet> ovenToGrill;

    @Autowired
    VealCutletRecipe vealCutletRecipe;

    @Autowired
    PorkCutletRecipe porkCutletRecipe;

    @Test
    public void toBakeOnGrillTest() {

        Grill grill = new GrillGas(new Gas(new Energy.Calorific(Energy.StateOfAggregation.GASEOUS.getCalorificValue())));
        Cutlet<VealMeat> vealMeatCutlet = vealCutletRecipe.cooked(0.125);
        assertEquals(vealMeatCutlet.getFinished(), Finished.RAW);

        ovenToGrill.setGrill(grill);
        ovenToGrill.toBake(vealMeatCutlet);
        assertEquals(vealMeatCutlet.getFinished(), Finished.FINISHED);
    }

    @Test
    public void barbecueTest() {
        Grill grill = new GrillCharCoal(new CharCoal(new Energy.Calorific(Energy.StateOfAggregation.SOLID.getCalorificValue())));
        Cutlet<PorkMeat> porkMeatCutlet = porkCutletRecipe.cooked(0.14);
        Cutlet<PorkMeat> porkMeatCutletClone = porkCutletRecipe.cooked( 0.14);
        assertEquals(porkMeatCutlet.getFinished(), Finished.RAW);
        assertEquals(porkMeatCutletClone.getFinished(), Finished.RAW);

        grill.barbecue(porkMeatCutlet);
        assertEquals(porkMeatCutlet.getFinished(), Finished.FINISHED);

        ovenToGrill.setGrill(grill);
        ovenToGrill.toBake(porkMeatCutletClone);
        assertEquals(porkMeatCutletClone.getFinished(), Finished.FINISHED);
    }
}
