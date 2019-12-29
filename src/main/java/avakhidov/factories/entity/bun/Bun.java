package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.Flour;

    public abstract class Bun extends Product<ParameterPrepareDough> {

    private boolean recipeReady = false;

    protected Bun() {
    }

    public Bun(ParameterPrepareDough<? extends Flour> prepareDough, boolean recipeReady, double weight) {
        super(prepareDough, weight);
        this.recipeReady = recipeReady;
    }

    public abstract void setKindDough();

    public boolean getRecipeReady() {
        return recipeReady;
    }

    public void setRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bun)) return false;
        Bun bun = (Bun) o;
        return getRecipeReady() == bun.getRecipeReady()
                && getWeight() == bun.getWeight()
                && getFinished().equals(bun.getFinished())
                &&  getMainIngredient().equals(bun.getMainIngredient());
    }

}
