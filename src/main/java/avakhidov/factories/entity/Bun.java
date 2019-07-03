package avakhidov.factories.entity;


public abstract class Bun {

    private Flour flour;

    private boolean recipeReady = false;

    public Bun(Flour flour, boolean recipeReady) {
        this.flour = flour;
        this.recipeReady = recipeReady;
    }

    public Flour getFlour() {
        return flour;
    }

    public boolean getRecipeReady() {
        return recipeReady;
    }

    public void setFlour(Flour flour) {
        this.flour = flour;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }
}
