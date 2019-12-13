package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;


public abstract class Cutlet<T extends Meat> extends Product<T> {

    private boolean recipeReady = false;

    private SesameBun sesameBun;

    public Cutlet(T meat, boolean recipeReady, double weight) {
        super(meat, weight);
        this.recipeReady = recipeReady;
    }

    protected Cutlet() {

    }

    public void setMeat(T meat) {
        super.setMainIngredient(meat);
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

    void setSesameBun(SesameBun sesameBun) {
        this.sesameBun = sesameBun;
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

        private SesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                          double weight) {
            super(parameterDough.toKneadTheDough(), recipeReady, weight);
            this.sesame = sesame;
            this.recipeReady = recipeReady;
            this.weight = weight;
            this.parameterDough = parameterDough;
        }

        @Override
        public void setKindDough() {
            super.getMainIngredient().setKindDough(
            DoughUtil.setParameterKindDoughFromMeat(getMeat()));
        }
    }

}
