package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;

public abstract class Bun {

    private ParameterPrepareDough prepareDough;

    private boolean recipeReady = false;

    public Bun(ParameterPrepareDough prepareDough, boolean recipeReady) {
        this.prepareDough = prepareDough;
        this.recipeReady = recipeReady;
    }

    protected abstract void setKindDough();

    public ParameterPrepareDough getPrepareDough() {
        return prepareDough;
    }

    public boolean getRecipeReady() {
        return recipeReady;
    }

    public void setPrepareDough(ParameterPrepareDough prepareDough) {
        this.prepareDough = prepareDough;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }
}
