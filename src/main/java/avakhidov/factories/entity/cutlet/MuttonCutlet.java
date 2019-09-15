package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.MuttonMeat;

public class MuttonCutlet extends Cutlet<MuttonMeat> {


    public MuttonCutlet(MuttonMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
