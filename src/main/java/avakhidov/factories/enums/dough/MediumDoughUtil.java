package avakhidov.factories.enums.dough;

import avakhidov.factories.enums.GrindingFlour;

public class MediumDoughUtil extends AbstractDoughUtil {

    @Override
    public KindDough findByGrinding(GrindingFlour grinding) {
        if (grinding.equals(GrindingFlour.MEDIUM)) {
            return KindDough.PUFF_PASTRY;
        } else {
            return findKindDoughByNext(grinding);
        }
    }
}
