package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class PorkCutlet extends Cutlet<PorkMeat> {

    public PorkCutlet(PorkMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }

    public PorkCutlet() {
        super();
    }

    public class BuilderPorkCutlet<P extends PorkCutlet, R extends BuilderPorkCutlet<? extends P, ?>> extends BuilderProduct<P, R> {

        boolean recipeReady = false;

        private BuilderPorkCutlet(P child) {
            super(child);
        }

        public R withRecipeReady(boolean recipeReady) {
            this.recipeReady = recipeReady;
            getNested().setRecipeReady(recipeReady);
            return self();
        }

        public R withSesameBun(BuildParameterPrepareDough prepareDough, Sesame sesame, double weightBun) {
            createSesameBun(prepareDough, recipeReady, sesame, weightBun);
            return self();
        }

    }

    private class FinalBuilderPorkCutlet extends BuilderPorkCutlet<PorkCutlet, FinalBuilderPorkCutlet> {

        private FinalBuilderPorkCutlet() {
            super(new PorkCutlet());
            injectReturnBuilder(this);
        }
    }

    public BuilderPorkCutlet<? extends PorkCutlet, ?> builder() {
        return new FinalBuilderPorkCutlet();
    }
}
