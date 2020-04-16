package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.meat.VealMeat;

public class VealCutlet extends Cutlet<VealMeat> {

    public VealCutlet(VealMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }

    VealCutlet() {

    }

    public static class BuilderVealCutlet<P extends VealCutlet, R extends BuilderVealCutlet<? extends P, ?>>
            extends BuilderProduct<P, R, VealMeat> {

        boolean recipeReady = false;

        private BuilderVealCutlet(P child) {
            super(child);
        }

        public R withRecipeReady(boolean recipeReady) {
            this.recipeReady = recipeReady;
            getNested().setRecipeReady(recipeReady);
            return self();
        }
    }

    private static class FinalBuilderVealCutlet extends BuilderVealCutlet<VealCutlet, FinalBuilderVealCutlet> {

        private FinalBuilderVealCutlet() {
            super(new VealCutlet());
            injectReturnBuilder(this);
        }
    }

    public static BuilderVealCutlet<? extends VealCutlet, ?> builderVealCutlet() {
        return new FinalBuilderVealCutlet();
    }
}
