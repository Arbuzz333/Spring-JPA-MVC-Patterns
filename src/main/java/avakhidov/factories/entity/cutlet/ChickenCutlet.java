package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.ChickenMeat;

public class ChickenCutlet extends Cutlet<ChickenMeat> {

    public ChickenCutlet(ChickenMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
