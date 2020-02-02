package avakhidov.factories.enums.dough;

import avakhidov.factories.enums.GrindingFlour;

public class FineDoughUtil extends AbstractDoughUtil {

    @Override
    public KindDough findByGrinding(GrindingFlour grinding) {
        if (grinding.equals(GrindingFlour.FINE)) {
            return KindDough.YEAST_DOUGH;
        } else {
            return findKindDoughByNext(grinding);
        }
    }
}
