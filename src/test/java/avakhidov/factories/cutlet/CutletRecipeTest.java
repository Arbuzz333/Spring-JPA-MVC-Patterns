package avakhidov.factories.cutlet;


import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;





@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CutletTestConfig.class)
public class CutletRecipeTest {

    @Autowired
    private List<Recipe<Cutlet<? extends Meat>>> recipeList;

    @Test
    public void recipeTest() {
        assertEquals(3, recipeList.size());

        PorkCutlet porkCutlet = (PorkCutlet) recipeList.get(0).cooked(0.125);
        ChickenCutlet chickenCutlet = (ChickenCutlet) recipeList.get(1).cooked(0.225);
        VealCutlet vealCutlet = (VealCutlet) recipeList.get(2).cooked(0.315);

        assertEquals(KindDough.YEAST_DOUGH, porkCutlet.getSesameBun().getMainIngredient().getKindDough());
        assertEquals(KindMeat.CHICKEN, chickenCutlet.getMainIngredient().getKindMeat());
        assertEquals(FatMeat.MEDIUM_FAT, vealCutlet.getMainIngredient().getFatMeat());

    }
}
