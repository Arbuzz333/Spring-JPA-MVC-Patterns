package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.BuildParameterPrepareDough;


public abstract class Cutlet<T extends Meat> extends Product<T> {

    public static final MainIngredientEnum mainIngredientEnum = MainIngredientEnum.MEAT;

    private boolean recipeReady = false;

    private SesameBun sesameBun;

    protected Cutlet(T meat, boolean recipeReady, double weight) {
        super(meat, weight);
        this.recipeReady = recipeReady;
    }

    protected Cutlet() {

    }

    private T getMeat() {
        return super.getMainIngredient();
    }

    public boolean isRecipeReady() {
        return recipeReady;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    public SesameBun getSesameBun() {
        return this.sesameBun;
    }

    public Sesame getSesame() {
        return this.sesameBun.sesame;
    }

    public ParameterPrepareDough getParameterPrepareDoughBun() {
        return this.sesameBun.getMainIngredient();
    }

    private SesameBun setNewSesameBun() {
        this.sesameBun = new SesameBun();
        return this.sesameBun;
    }

    public SesameBun createSesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                                     double weight) {
        this.sesameBun = new SesameBun(parameterDough, recipeReady, sesame, weight);
        return sesameBun;
    }

    public class SesameBun extends Bun {

        private Sesame sesame;
        boolean recipeReady;
        double weight;
        BuildParameterPrepareDough parameterDough;

        SesameBun() {
            super();
        }

        private SesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                          double weight) {
            super(parameterDough.toKneadTheDough(), recipeReady, weight);
            this.sesame = sesame;
            this.recipeReady = recipeReady;
            this.weight = weight;
            this.parameterDough = parameterDough;
            setKindDough();
        }

        void setKindDough(KindDough kindDough) {
            super.getMainIngredient().setKindDough(kindDough);
        }

        @Override
        public void setKindDough() {
            super.getMainIngredient().setKindDough(
            DoughUtil.setParameterKindDoughFromMeat(getMeat()));
        }
    }

    public class BuilderSesameBun<P extends SesameBun, R extends BuilderSesameBun<? extends P, ?>>
            extends BuilderProduct<P, R, ParameterPrepareDough> {

        BuilderSesameBun(P child) {
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

    private class FinalBuilderSesameBun extends BuilderSesameBun<SesameBun, FinalBuilderSesameBun> {

        private FinalBuilderSesameBun() {
            super(setNewSesameBun());
            injectReturnBuilder(this);
        }
    }

    public BuilderSesameBun<? extends SesameBun, ?> builderSesameBun() {
       return new FinalBuilderSesameBun();
    }

}
