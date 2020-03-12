package avakhidov.factories;


import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PorkCutletBuilderTest {

    @Autowired
    private MeatServiceImpl meatService;

    @Test
    public void buildPorkCutletTest() {
        PorkCutlet porkCutlet = PorkCutlet.builderPorkCutlet()
                .withMainIngredient((PorkMeat) meatService.buildMeat(PorkMeat.class))
                .withWeight(0.12)
                .withFinished(Finished.RAW)
                .withRecipeReady(true)
                .build();

        porkCutlet.builderSesameBun()
                .withRecipeReady(true)
                .withFinished(Finished.RAW)
                .withMainIngredient(ParameterDoughEnum.CORN_FLOUR_COARSE.toKneadTheDough())
                .withKindDough(KindDough.SHORTCRUST_PASTRY)
                .withWeight(0.075)
                .build();

        assertEquals(porkCutlet.getSesameBun().getMainIngredient().getKindDough(), KindDough.SHORTCRUST_PASTRY);
        assertEquals(porkCutlet.getMainIngredient().getFatMeat(), FatMeat.SPECK);
    }

    @Test
    public void OuterBuildPorkCutletTest() {
        PorkCutlet porkCutlet = PorkCutlet.outerBuilderPorkCutlet()
                .withRecipeReady(true)
                .withMainIngredient((PorkMeat) meatService.buildMeat(PorkMeat.class))
                .withFinished(Finished.RAW)
                .withWeight(0.135)
                .withSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE, new Sesame())
                .withKindDough(KindDough.CHOUX_PASTRY)
                .build();

        assertEquals(porkCutlet.getMainIngredient().getFatMeat(), FatMeat.SPECK);
        assertEquals(porkCutlet.getSesameBun().getFinished(), Finished.RAW);
        assertEquals(porkCutlet.getSesameBun().getMainIngredient().getKindDough(), KindDough.CHOUX_PASTRY);

        porkCutlet.getSesameBun().setKindDough();
        assertEquals(porkCutlet.getSesameBun().getMainIngredient().getKindDough(), KindDough.YEAST_DOUGH);
    }
}
