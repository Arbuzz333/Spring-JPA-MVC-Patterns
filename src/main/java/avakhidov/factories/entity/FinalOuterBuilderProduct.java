package avakhidov.factories.entity;

import avakhidov.factories.service.MainIngredient;

public class FinalOuterBuilderProduct<T extends MainIngredient> extends OuterBuilderProduct<Product<T>, FinalOuterBuilderProduct<T>, T> {

    FinalOuterBuilderProduct() {
        super(new Product<>());
        injectReturnBuilder(this);
    }

    public OuterBuilderProduct<? extends Product<T>, ?, T> builder() {
        return new FinalOuterBuilderProduct<>();
    }
}