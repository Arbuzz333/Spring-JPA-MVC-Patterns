package avakhidov.factories.enums.dough;

import avakhidov.factories.enums.GrindingFlour;

public enum DoughUtil {
    ;
    public static KindDough setParameterKindDough(GrindingFlour grinding) {

        if (grinding.equals(GrindingFlour.FINE)) {
            return KindDough.YEAST_DOUGH;
        } else {
            if (grinding.equals(GrindingFlour.MEDIUM)) {
                return KindDough.PUFF_PASTY;
            } else {
                return KindDough.SHORTCRUST_PASTY;
            }
        }

    }

}
