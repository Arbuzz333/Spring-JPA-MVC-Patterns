package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;

public class Product<T> {

    private T prepack;

    public double getWeight() {
        return weight;
    }

    private double weight;

    private Finished finished;

    public Product(T prepack, double weight) {
        this.prepack = prepack;
        this.weight = weight;
    }

    public Product(T prepack, double weight, Finished finished) {
        this.prepack = prepack;
        this.weight = weight;
        this.finished = finished;
    }

    public Product() {
    }

    public T getPrepack() {
        return prepack;
    }

    public Finished getFinished() {
        return finished;
    }

    protected Product<T> setPrepack(T prepack) {
        this.prepack = prepack;
        return this;
    }

    public Product<T> setFinished(Finished finished) {
        this.finished = finished;
        return this;
    }

}
