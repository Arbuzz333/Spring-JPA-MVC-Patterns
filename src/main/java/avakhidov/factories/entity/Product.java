package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.MainIngredient;

import java.util.Objects;
import java.util.UUID;

public class Product<T extends MainIngredient> {

    protected UUID uuid;

    protected boolean recipeReady = false;

    private T mainIngredient;

    private double weight;

    private Finished finished;

    {
        this.uuid = UUID.randomUUID();
    }

    protected Product(T mainIngredient, double weight) {
        this.mainIngredient = mainIngredient;
        this.weight = weight;
    }

    public Product() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public T getMainIngredient() {
        return mainIngredient;
    }

    public Finished getFinished() {
        return finished;
    }

    Product<T> setMainIngredient(T mainIngredient) {
        this.mainIngredient = mainIngredient;
        return this;
    }

    public Product<T> setFinished(Finished finished) {
        this.finished = finished;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public static class BuilderProduct<P extends Product<T>, R extends BuilderProduct<? extends P, ?, T>, T extends MainIngredient> extends BuilderBase<P, R, T> {

        protected BuilderProduct(P child) {
            super(child);
        }

        public R withMainIngredient(T mainIngredient) {
            getNested().setMainIngredient(mainIngredient);
            return self();
        }

        public R withWeight(double weight) {
            getNested().setWeight(weight);
            return self();
        }

        public R withFinished(Finished finished) {
            getNested().setFinished(finished);
            return self();
        }

    }
    private static class FinalBuilderProduct<T extends MainIngredient> extends BuilderProduct<Product<T>, FinalBuilderProduct<T>, T> {

        private FinalBuilderProduct() {
            super(new Product<>());
            injectReturnBuilder(this);
        }

    }
    public static BuilderProduct<? extends Product, ?, ?> builderProduct() {
        return new FinalBuilderProduct<>();
    }

    public OuterBuilderProduct<? extends Product<T>, ?, T> outerBuilder() {
        return new FinalOuterBuilderProduct<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product<?> product = (Product<?>) o;
        return Double.compare(product.getWeight(), getWeight()) == 0 &&
                Objects.equals(getMainIngredient(), product.getMainIngredient()) &&
                getFinished() == product.getFinished();
    }
}
