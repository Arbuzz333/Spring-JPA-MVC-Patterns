package avakhidov.factories.enums.dough;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public enum DoughUtil {
    ;
    public static KindDough setParameterKindDough(GrindingFlour grinding) {

        if (grinding.equals(GrindingFlour.FINE)) {
            return KindDough.YEAST_DOUGH;
        } else {
            if (grinding.equals(GrindingFlour.MEDIUM)) {
                return KindDough.PUFF_PASTRY;
            } else {
                return KindDough.SHORTCRUST_PASTRY;
            }
        }
    }

    public static KindDough setParameterKindDoughFromFlour(KindFlour flour) {

        if (flour.equals(KindFlour.BUCKWHEAT)) {
            return KindDough.CHOUX_PASTRY;
        } else {
            if (flour.equals(KindFlour.CORN)) {
                return KindDough.SHORTCRUST_PASTRY;
            } else {
                return KindDough.YEAST_DOUGH;
            }
        }
    }

    public static KindDough setParameterKindDoughFromAMPM(LocalTime time) {
        DateFormat formatter = new SimpleDateFormat("a");
        String ampm = formatter.format(time);

        if (ampm.equals("AM")) {
            return KindDough.PUFF_PASTRY;
        } else {
            return KindDough.YEAST_DOUGH;
        }
    }

    public static KindDough setParameterKindDoughFromMeat(Meat meat) {

        if (meat.equals(KindMeat.BEEF)) {
            return KindDough.CHOUX_PASTRY;
        } else {
            if (meat.equals(KindMeat.CHICKEN)) {
                return KindDough.SHORTCRUST_PASTRY;
            } else {
                return KindDough.YEAST_DOUGH;
            }
        }
    }

}
