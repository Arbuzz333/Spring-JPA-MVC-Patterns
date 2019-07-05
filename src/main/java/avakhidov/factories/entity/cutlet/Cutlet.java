package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Bun;
import avakhidov.factories.entity.Flour;
import avakhidov.factories.entity.Sesame;

public abstract class Cutlet<T> {

    private T meat;

    private boolean recipeReady = false;

    private double weight;

    public Cutlet(T meat, boolean recipeReady, double weight) {
        this.meat = meat;
        this.recipeReady = recipeReady;
        this.weight = weight;
    }

    public class SesameBun extends Bun {

        public Sesame sesame;

        public SesameBun(Flour flour, boolean recipeReady, Sesame sesame) {
            super(flour, recipeReady);
            this.sesame = sesame;
        }
    }

}
