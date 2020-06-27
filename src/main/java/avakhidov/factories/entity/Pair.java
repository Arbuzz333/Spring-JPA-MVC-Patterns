package avakhidov.factories.entity;

public class Pair<T, E> {

    private T key;

    private E value;

    public Pair(T key, E value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }

}
