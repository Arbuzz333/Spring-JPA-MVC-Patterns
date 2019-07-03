package avakhidov.factories.entity;

public abstract class PrepareBase<T> extends Prepare {

    private T ingredient;

    public PrepareBase(boolean recipeReady, T ingredient) {
        super(recipeReady);
        this.ingredient = ingredient;
    }

    public T getIngredient() {
        return ingredient;
    }

    public void setIngredient(T ingredient) {
        this.ingredient = ingredient;
    }

}
