package avakhidov.factories.entity.cutlet;

public class FinalBuilderPorkCutlet2 extends BuilderPorkCutlet2<PorkCutlet, FinalBuilderPorkCutlet2> {

    FinalBuilderPorkCutlet2() {
        super(new PorkCutlet());
        injectReturnBuilder(this);
    }

//    public static BuilderPorkCutlet2<? extends PorkCutlet, ?> builderCutlet() {
//        return new FinalBuilderPorkCutlet2();
//    }

}