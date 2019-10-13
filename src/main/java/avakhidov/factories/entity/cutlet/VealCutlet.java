package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.VealMeat;

public class VealCutlet extends Cutlet<VealMeat> {

    public VealCutlet(VealMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
