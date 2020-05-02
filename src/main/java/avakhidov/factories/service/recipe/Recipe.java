package avakhidov.factories.service.recipe;


public interface Recipe<T> {

    T cooked(double weight);
}
