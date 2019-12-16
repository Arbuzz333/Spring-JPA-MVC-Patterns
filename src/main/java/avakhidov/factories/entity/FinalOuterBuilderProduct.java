package avakhidov.factories.entity;

public class FinalOuterBuilderProduct<T> extends OuterBuilderProduct<Product<T>, FinalOuterBuilderProduct<T>, T> {

    FinalOuterBuilderProduct() {
        super(new Product<>());
        injectReturnBuilder(this);
    }

    public OuterBuilderProduct<? extends Product<T>, ?, T> builder() {
        return new FinalOuterBuilderProduct<>();
    }
}