package avakhidov.factories;


import avakhidov.factories.entity.cutlet.BuilderPorkCutlet2;
import avakhidov.factories.entity.cutlet.FinalBuilderPorkCutlet2;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PorkCutletBuilderTest {

    @Test
    public void buildPorkCutletTest() {
//        PorkCutlet.BuilderPorkCutlet
        PorkCutlet porkCutlet = PorkCutlet.builderCutlet()
                .withMainIngredient(new PorkMeat(FatMeat.MEDIUMFAT, new Pig()))
                .withWeight(0.12)
                .withFinished(Finished.RAW)
                .withRecipeReady(true)
                .withSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE, new Sesame(), 0.075)
                .build();

//        porkCutlet.getSesameBun().setKindDough();

//        assertEquals(porkCutlet.getSesameBun().getMainIngredient().getKindDough(), KindDough.SHORTCRUST_PASTRY);
        assertEquals(porkCutlet.getMainIngredient().getFatMeat(), FatMeat.MEDIUMFAT);
    }

    @Test
    public void buildPorkCutletTest2() {
        PorkCutlet porkCutlet = PorkCutlet.builderCutlet2()
                .withRecipeReady(true)
                .withMainIngredient(new PorkMeat(FatMeat.MEDIUMFAT, new Pig()))
                .withFinished(Finished.RAW)
                .withWeight(0.135)
                .withSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE, new Sesame())
                .build();

        porkCutlet.getSesameBun().setKindDough();

        assertEquals(porkCutlet.getMainIngredient().getFatMeat(), FatMeat.MEDIUMFAT);
        assertEquals(porkCutlet.getSesameBun().getFinished(), Finished.RAW);
    }
}
