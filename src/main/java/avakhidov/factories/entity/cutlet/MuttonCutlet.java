package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.listeners.GenericSpringEvent;


public class MuttonCutlet extends Cutlet<MuttonMeat> {

    private GenericSpringEvent<MuttonCutlet> listener;

    private MuttonCutlet() {
        super();
        this.listener = new GenericSpringEvent<>(this);
    }

    public MuttonCutlet(MuttonMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
        this.listener = new GenericSpringEvent<>(this);
    }

    public GenericSpringEvent<MuttonCutlet> getListener() {
        return listener;
    }

    public static class BuilderMuttonCutlet<P extends MuttonCutlet, R extends BuilderMuttonCutlet<? extends P, ?>>
            extends BuilderProduct<P, R, MuttonMeat> {

        boolean recipeReady = false;

        private BuilderMuttonCutlet(P child) {
            super(child);
        }

        public R withRecipeReady(boolean recipeReady) {
            this.recipeReady = recipeReady;
            getNested().setRecipeReady(recipeReady);
            return self();
        }

        public R withMainIngredient(FatMeat fatMeat) {
            R self = self();
            self.withMainIngredient(new MuttonMeat(fatMeat, new Sheep()));
            return self();
        }

    }

    private static class FinalBuilderMuttonCutlet extends BuilderMuttonCutlet<MuttonCutlet, FinalBuilderMuttonCutlet> {

        private FinalBuilderMuttonCutlet() {
            super(new MuttonCutlet());
            injectReturnBuilder(this);
        }
    }

    public static BuilderMuttonCutlet<? extends MuttonCutlet, ?> builderMuttonCutlet() {
        return new FinalBuilderMuttonCutlet();
    }

    public static Cutlet<MuttonMeat> cooked(double weight) {

        MuttonCutlet muttonCutlet = new MuttonCutlet(new MuttonMeat(FatMeat.SPECK, new Sheep()), true, weight);
        muttonCutlet.setFinished(Finished.RAW);

        muttonCutlet.createSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE, true, new Sesame(), weight * 0.8);

        return muttonCutlet;
    }
}
