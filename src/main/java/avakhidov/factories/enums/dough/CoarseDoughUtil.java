package avakhidov.factories.enums.dough;

import avakhidov.factories.enums.GrindingFlour;

public class CoarseDoughUtil extends AbstractDoughUtil {

    @Override
    public KindDough findByGrinding(GrindingFlour grinding) {
        if (grinding.equals(GrindingFlour.COARSE)) {
            return KindDough.SHORTCRUST_PASTRY;
        } else {
            return findKindDoughByNext(grinding);
        }
    }
}
