package avakhidov.factories.entity.cutlet;

public class PorkCutlet extends Cutlet<PorkMeat> {

    public PorkCutlet(PorkMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }
}
