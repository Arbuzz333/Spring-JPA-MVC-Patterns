package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;

public abstract class Bun extends Product<ParameterPrepareDough> {

    private boolean recipeReady = false;

    private double weight;

    public Bun(ParameterPrepareDough prepareDough, boolean recipeReady, double weight) {
        super(prepareDough);
        this.recipeReady = recipeReady;
        this.weight = weight;
    }

    public abstract void setKindDough();

    public boolean getRecipeReady() {
        return recipeReady;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    public double getWeight() {
        return weight;
    }
}
