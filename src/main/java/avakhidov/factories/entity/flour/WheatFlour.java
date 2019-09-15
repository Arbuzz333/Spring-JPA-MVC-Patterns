package avakhidov.factories.entity.flour;

import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;

public class WheatFlour extends Flour {

    public WheatFlour(GrindingFlour grinding) {
        super(KindFlour.WHEAT, grinding);
    }
}
