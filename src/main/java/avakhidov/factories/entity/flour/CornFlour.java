package avakhidov.factories.entity.flour;

import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;

public class CornFlour extends Flour {

    public CornFlour(GrindingFlour grinding) {
        super(KindFlour.CORN, grinding);
    }
}
