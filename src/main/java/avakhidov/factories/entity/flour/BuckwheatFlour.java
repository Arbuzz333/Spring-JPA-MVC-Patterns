package avakhidov.factories.entity.flour;

import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;

public class BuckwheatFlour extends Flour {

    public BuckwheatFlour(GrindingFlour grinding) {
        super(KindFlour.BUCKWHEAT, grinding);
    }
}
