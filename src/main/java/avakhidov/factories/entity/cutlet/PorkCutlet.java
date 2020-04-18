package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;

import static avakhidov.factories.enums.dough.ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM;

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

        public R withMainIngredient(FatMeat fatMeat) {
            R self = self();
            self.withMainIngredient(new PorkMeat(fatMeat, new Pig()));
            return self();
        }

        public R withSeasamBun(double weight) {
            getNested().builderSesameBun()
                    .withMainIngredient(BUCKWHEAT_FLOUR_MEDIUM.toKneadTheDough())
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withKindDough(KindDough.SHORTCRUST_PASTRY).withWeight(weight)
                    .build();
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
