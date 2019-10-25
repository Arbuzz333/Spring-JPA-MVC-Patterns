package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;

public abstract class Bun extends Product<ParameterPrepareDough> {

    private boolean recipeReady = false;

    public Bun(ParameterPrepareDough prepareDough, boolean recipeReady) {
        super(prepareDough);
        this.recipeReady = recipeReady;
    }

    protected abstract void setKindDough();

    public boolean getRecipeReady() {
        return recipeReady;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }
}
