package avakhidov.springpatterns.flour.entyties;


import avakhidov.springpatterns.flour.enums.GrindingFlour;
import avakhidov.springpatterns.flour.enums.KindFlour;





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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flour)) return false;
        Flour flour = (Flour) o;
        return getKind() == flour.getKind() &&
                getGrinding() == flour.getGrinding();
    }

}
