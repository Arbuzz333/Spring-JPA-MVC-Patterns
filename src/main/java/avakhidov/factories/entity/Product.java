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

}
