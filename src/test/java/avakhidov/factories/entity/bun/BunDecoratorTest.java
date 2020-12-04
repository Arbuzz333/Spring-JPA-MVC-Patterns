package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class BunDecoratorTest {

    @Test
    public void addIngredient() {
        BuckwheatBun bun = BuckwheatBun.builderBuckwheat()
                .withMainIngredient(ParameterDoughEnum.CORN_FLOUR_COARSE.toKneadTheDough())
                .withKindDough(KindDough.YEAST_DOUGH)
                .withFinished(Finished.FINISHED)
                .withRecipeReady(true)
                .withWeight(0.125)
                .build();

        BunDecorator peanutDecorator = new BunBunPeanutDecorator(new BunBunWalnutDecorator(bun));
        peanutDecorator.addIngredient("first grade");

        List<Ingredient> ingredientList = bun.getIngredientList();

        assertEquals(3, ingredientList.size());

    }

}