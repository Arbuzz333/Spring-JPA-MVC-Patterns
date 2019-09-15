package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.service.BuildParameterPrepareDough;

public abstract class Cutlet<T> {

    private T meat;

    private boolean recipeReady = false;

    private double weight;

    private SesameBun sesameBun;

    public Cutlet(T meat, boolean recipeReady, double weight) {
        this.meat = meat;
        this.recipeReady = recipeReady;
        this.weight = weight;
    }

    public void setMeat(T meat) {
        this.meat = meat;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public T getMeat() {
        return meat;
    }

    public boolean isRecipeReady() {
        return recipeReady;
    }

    public double getWeight() {
        return weight;
    }

    public SesameBun getSesameBun() {
        return sesameBun;
    }

    public Sesame getSesame() {
        return this.sesameBun.sesame;
    }

    public SesameBun createSesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame) {
        this.sesameBun = new SesameBun(parameterDough, recipeReady, sesame);
        return sesameBun;
    }

    public class SesameBun extends Bun {

        private Sesame sesame;

        private SesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame) {
            super(parameterDough.toKneadTheDough(), recipeReady);
            this.sesame = sesame;
        }

        @Override
        protected void setKindDough() {
            //ToDo
        }
    }

}
