package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public abstract class Cutlet<T extends Meat> {

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
        return this.sesameBun;
    }

    public Sesame getSesame() {
        return this.sesameBun.sesame;
    }

    public ParameterPrepareDough getParameterPrepareDoughBun() {
        return this.sesameBun.getPrepack();
    }

    public SesameBun createSesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                                     int temperature, LocalTime time) {
        this.sesameBun = new SesameBun(parameterDough, recipeReady, sesame, temperature, time);
        return sesameBun;
    }

    public class SesameBun extends Bun {

        private Sesame sesame;

        private SesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                          int temperature, LocalTime time) {
            super(parameterDough.toKneadTheDough(temperature, time), recipeReady);
            this.sesame = sesame;
        }

        @Override
        public void setKindDough() {
            super.getPrepack().setKindDough(
            DoughUtil.setParameterKindDoughFromMeat(meat));
        }
    }

}
