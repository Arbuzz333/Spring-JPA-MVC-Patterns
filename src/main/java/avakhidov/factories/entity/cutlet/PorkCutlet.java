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

    public static class BuilderPorkCutlet<P extends PorkCutlet, R extends BuilderPorkCutlet<? extends P, ?>>
            extends BuilderProduct<P, R, PorkMeat> {

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
            SesameBun sesameBun = createSesameBun(prepareDough, recipeReady, sesame, weightBun);
            getNested().setSesameBun(sesameBun);
            return self();
        }

    }

    private static class FinalBuilderPorkCutlet extends BuilderPorkCutlet<PorkCutlet, FinalBuilderPorkCutlet> {

        private FinalBuilderPorkCutlet() {
            super(new PorkCutlet());
            injectReturnBuilder(this);
        }
    }

    public static BuilderPorkCutlet<? extends PorkCutlet, ?> builderCutlet() {
        return new FinalBuilderPorkCutlet();
    }

    public static BuilderPorkCutlet2<? extends PorkCutlet, ?> builderCutlet2() {
        return new FinalBuilderPorkCutlet2();
    }
}
