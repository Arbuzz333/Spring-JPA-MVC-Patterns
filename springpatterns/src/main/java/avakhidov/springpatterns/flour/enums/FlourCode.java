package avakhidov.springpatterns.flour.enums;


public enum FlourCode {

    CORN_FLOUR_COARSE(GrindingFlour.COARSE, KindFlour.CORN),
    CORN_FLOUR_MEDIUM(GrindingFlour.MEDIUM, KindFlour.CORN),
    BUCKWHEAT_FLOUR_COARSE(GrindingFlour.COARSE, KindFlour.BUCKWHEAT),
    BUCKWHEAT_FLOUR_MEDIUM(GrindingFlour.MEDIUM, KindFlour.BUCKWHEAT),
    WHEAT_FLOUR_FINE(GrindingFlour.FINE, KindFlour.WHEAT),
    ;

    private final GrindingFlour grindingFlour;
    private final KindFlour kindFlour;

    FlourCode(GrindingFlour coarse, KindFlour kindFlour) {
        this.grindingFlour = coarse;
        this.kindFlour = kindFlour;

    }

    public GrindingFlour getGrindingFlour() {
        return grindingFlour;
    }

    public KindFlour getKindFlour() {
        return kindFlour;
    }
}
