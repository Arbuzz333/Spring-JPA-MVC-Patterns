package avakhidov.factories.entity.cutlet;

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
                , chickenCutlet.getSesame()
                , chickenCutlet.getSesameBun().weight);
        super.getSesameBun().setKindDough(chickenCutlet.getSesameBun().getMainIngredient().getKindDough());

    }

    public ChickenCutlet cloneChickenCutlet(ChickenCutlet cutlet) {
        return new ChickenCutlet(cutlet);
    }
}
