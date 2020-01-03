package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.PorkMeat;

public class PorkCutlet extends Cutlet<PorkMeat> {

    public PorkCutlet(PorkMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }

    PorkCutlet() {
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

    }

    private static class FinalBuilderPorkCutlet extends BuilderPorkCutlet<PorkCutlet, FinalBuilderPorkCutlet> {

        private FinalBuilderPorkCutlet() {
            super(new PorkCutlet());
            injectReturnBuilder(this);
        }
    }

    public static BuilderPorkCutlet<? extends PorkCutlet, ?> builderPorkCutlet() {
        return new FinalBuilderPorkCutlet();
    }

    public static OuterBuilderPorkCutlet<? extends PorkCutlet, ?> outerBuilderPorkCutlet() {
        return new FinalOuterBuilderPorkCutlet();
    }
}
