package avakhidov.factories.entity;


import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;

public abstract class Flour {

    private KindFlour kind;

    private GrindingFlour grinding;

    public Flour(KindFlour kind, GrindingFlour grinding) {
        this.kind = kind;
        this.grinding = grinding;
    }

    public KindFlour getKind() {
        return kind;
    }

    public GrindingFlour getGrinding() {
        return grinding;
    }

    public void setKind(KindFlour kind) {
        this.kind = kind;
    }

    public void setGrinding(GrindingFlour grinding) {
        this.grinding = grinding;
    }
}
