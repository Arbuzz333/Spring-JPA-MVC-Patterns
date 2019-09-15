package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.PorkMeat;

public class PorkCutlet extends Cutlet<PorkMeat> {

    public PorkCutlet(PorkMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
