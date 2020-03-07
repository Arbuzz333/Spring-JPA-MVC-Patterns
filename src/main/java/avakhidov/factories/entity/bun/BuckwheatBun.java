package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.enums.dough.KindDough;

public class BuckwheatBun extends Bun {

    public BuckwheatBun(ParameterPrepareDough<BuckwheatFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    private BuckwheatBun() {
//        setKindDough();
    }

    @Override
    public void setKindDough() {
        super.getMainIngredient().setKindDough(
        DoughUtil.setParameterKindDough(super.getMainIngredient().getFlour().getGrinding()));
    }

    public static class BuilderBuckwheatBun<P extends BuckwheatBun, R extends BuilderBuckwheatBun<? extends P, ?>>
            extends BuilderProduct<P, R, ParameterPrepareDough> {

        BuilderBuckwheatBun(P child) {
            super(child);
        }

        public R withRecipeReady(boolean recipeReady) {
            getNested().setRecipeReady(recipeReady);
            return self();
        }

        public R withKindDough(KindDough kindDough) {
            getNested().getMainIngredient().setKindDough(kindDough);
            return self();
        }

    }

    private static class FinalBuilderBuckwheatBun extends BuilderBuckwheatBun<BuckwheatBun, FinalBuilderBuckwheatBun> {

        private FinalBuilderBuckwheatBun() {
            super(new BuckwheatBun());
            injectReturnBuilder(this);
        }
    }

    public static BuilderBuckwheatBun<? extends BuckwheatBun, ?> builderBuckwheat() {
        return new FinalBuilderBuckwheatBun();
    }
}
