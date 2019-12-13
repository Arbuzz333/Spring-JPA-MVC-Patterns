package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.enums.dough.DoughUtil;

public class CornBun extends Bun {

    private CornBun() {
        super();
    }

    public CornBun(ParameterPrepareDough<CornFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getMainIngredient().setKindDough(
                DoughUtil.setParameterKindDoughFromFlour(super.getMainIngredient().getFlour().getKind())
        );
    }

    public static class BuilderCornBun<P extends CornBun, R extends BuilderCornBun<? extends P, ?>>
            extends BuilderProduct<P, R, ParameterPrepareDough> {

        protected BuilderCornBun(P child) {
            super(child);
        }

        public R withRecipeReady(boolean recipeReady) {
            getNested().setRecipeReady(recipeReady);
            return self();
        }

//        public R withParameterDougt(ParameterPrepareDough<CornFlour> parameterDough) {
//            getNested().setMainIngredient(parameterDough);
//            return self();
//        }

    }

    private static class FinalBuilderCornBun extends BuilderCornBun<CornBun, FinalBuilderCornBun> {

        private FinalBuilderCornBun() {
            super(new CornBun());
            injectReturnBuilder(this);
        }
    }
}
