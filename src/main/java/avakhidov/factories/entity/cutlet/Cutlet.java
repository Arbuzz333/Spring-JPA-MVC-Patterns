package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Bun;
import avakhidov.factories.entity.Flour;
import avakhidov.factories.entity.Sesame;

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

    public SesameBun createSesameBun(Flour flour, boolean recipeReady, Sesame sesame) {
        this.sesameBun = new SesameBun(flour, recipeReady, sesame);
        return sesameBun;
    }

    public class SesameBun extends Bun {

        private Sesame sesame;

        private SesameBun(Flour flour, boolean recipeReady, Sesame sesame) {
            super(flour, recipeReady);
            this.sesame = sesame;
        }
    }

}
