package avakhidov.factories.entity;

public abstract class Prepare {

    private boolean recipeReady;

    public Prepare(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    public boolean isRecipeReady() {
        return recipeReady;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

}
