package avakhidov.springpatterns.flour.entyties;


import avakhidov.springpatterns.flour.enums.GrindingFlour;
import avakhidov.springpatterns.flour.enums.KindFlour;

public class CornFlour extends Flour {

    public CornFlour(GrindingFlour grinding) {
        super(KindFlour.CORN, grinding);
    }

}
