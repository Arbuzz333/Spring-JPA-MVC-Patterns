package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;

public class ChickenCutlet extends Cutlet<ChickenMeat> {

    public ChickenCutlet(ChickenMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }

    private ChickenCutlet(ChickenCutlet chickenCutlet) {
        super(new ChickenMeat(chickenCutlet.getMainIngredient().getFatMeat(), new Chicken())
                , chickenCutlet.isRecipeReady()
                , chickenCutlet.getWeight());
        super.createSesameBun(
                chickenCutlet.getSesameBun().parameterDough
                , chickenCutlet.getSesameBun().recipeReady
                , new Sesame()
                , chickenCutlet.getSesameBun().temperature
                , chickenCutlet.getSesameBun().time
                , chickenCutlet.getSesameBun().weight);

    }

    public ChickenCutlet cloneChickenCutlet(ChickenCutlet cutlet) {
        return new ChickenCutlet(cutlet);
    }
}
