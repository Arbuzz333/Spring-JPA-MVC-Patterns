package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.VealMeat;

public class VeatCutlet extends Cutlet<VealMeat> {

    public VeatCutlet(VealMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
