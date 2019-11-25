package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public abstract class Cutlet<T extends Meat> extends Product<T> {

    private boolean recipeReady = false;

    private SesameBun sesameBun;

    public Cutlet(T meat, boolean recipeReady, double weight) {
        super(meat, weight);
        this.recipeReady = recipeReady;
    }

    public void setMeat(T meat) {
        super.setMainIngredient(meat);
    }

    private T getMeate() {
        return super.getMainIngredient();
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    public boolean isRecipeReady() {
        return recipeReady;
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

    public SesameBun createSesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                                     int temperature, LocalTime time, double weight) {
        this.sesameBun = new SesameBun(parameterDough, recipeReady, sesame, temperature, time, weight);
        return sesameBun;
    }

    public class SesameBun extends Bun {

        private Sesame sesame;

        private SesameBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, Sesame sesame,
                          int temperature, LocalTime time, double weight) {
            super(parameterDough.toKneadTheDough(temperature, time), recipeReady, weight);
            this.sesame = sesame;
        }

        @Override
        public void setKindDough() {
            super.getMainIngredient().setKindDough(
            DoughUtil.setParameterKindDoughFromMeat(getMeate()));
        }
    }

}
