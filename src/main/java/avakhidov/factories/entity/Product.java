package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;

public class Product<T> {

    private T prepack;

    private Finished finished;

    public Product(T prepack, Finished finished) {
        this.prepack = prepack;
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

    public Product<T> setPrepack(T prepack) {
        this.prepack = prepack;
        return this;
    }

    public Product<T> setFinished(Finished finished) {
        this.finished = finished;
        return this;
    }

}
