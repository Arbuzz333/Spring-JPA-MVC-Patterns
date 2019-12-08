package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;

public class Product<T> {

    private T mainIngredient;

    private double weight;

    private Finished finished;

    public Product(T mainIngredient, double weight) {
        this.mainIngredient = mainIngredient;
        this.weight = weight;
    }

    public Product(T mainIngredient, double weight, Finished finished) {
        this.mainIngredient = mainIngredient;
        this.weight = weight;
        this.finished = finished;
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

    protected Product<T> setMainIngredient(T mainIngredient) {
        this.mainIngredient = mainIngredient;
        return this;
    }

    public Product<T> setFinished(Finished finished) {
        this.finished = finished;
        return this;
    }

    public class BuilderProduct<P extends Product<T>, R extends BuilderProduct<? extends P, ?>> extends BuilderBase<P, R> {

        protected T mainIngredient;

        protected double weight;

        protected Finished finished;

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

    private class FinalBuilderProduct extends BuilderProduct<Product<T>, FinalBuilderProduct> {

        private FinalBuilderProduct() {
            super(new Product<>());
            injectReturnBuilder(this);
        }
    }

    public BuilderProduct<? extends Product<T>, ?> builder() {
        return new FinalBuilderProduct();
    }

}
