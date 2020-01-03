package avakhidov.factories.entity.cutlet;

class FinalOuterBuilderPorkCutlet extends OuterBuilderPorkCutlet<PorkCutlet, FinalOuterBuilderPorkCutlet> {

    FinalOuterBuilderPorkCutlet() {
        super(new PorkCutlet());
        injectReturnBuilder(this);
    }

}