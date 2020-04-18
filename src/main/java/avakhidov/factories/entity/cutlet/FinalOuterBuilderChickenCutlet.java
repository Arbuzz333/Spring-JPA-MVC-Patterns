package avakhidov.factories.entity.cutlet;


class FinalOuterBuilderChickenCutlet extends OuterBuilderChickenCutlet<ChickenCutlet, FinalOuterBuilderChickenCutlet> {

    FinalOuterBuilderChickenCutlet() {
        super(new ChickenCutlet());
        injectReturnBuilder(this);
    }
}
