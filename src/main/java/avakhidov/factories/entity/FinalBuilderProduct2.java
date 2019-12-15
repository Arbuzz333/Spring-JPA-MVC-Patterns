package avakhidov.factories.entity;

public class FinalBuilderProduct2<T> extends BuilderProduct2<Product<T>, FinalBuilderProduct2<T>, T> {

    FinalBuilderProduct2() {
        super(new Product<>());
        injectReturnBuilder(this);
    }

    public BuilderProduct2<? extends Product<T>, ?, T> builder() {
        return new FinalBuilderProduct2<>();
    }
}