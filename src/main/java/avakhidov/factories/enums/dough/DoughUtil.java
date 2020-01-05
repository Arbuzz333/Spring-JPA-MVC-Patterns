package avakhidov.factories.enums.dough;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum DoughUtil {
    ;
    public static KindDough setParameterKindDough(GrindingFlour grinding) {

        AbstractDoughUtil doughUtil = new FineDoughUtil();
        doughUtil.setNext(new MediumDoughUtil()).setNext(new CoarseDoughUtil());
        return doughUtil.findByGrinding(grinding);

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String ampm = formatter.format(time);

        if (ampm.contains("AM")) {
            return KindDough.PUFF_PASTRY;
        } else {
            return KindDough.YEAST_DOUGH;
        }
    }

    public static KindDough setParameterKindDoughFromMeat(Meat meat) {

        if (meat.getKindMeat().equals(KindMeat.BEEF)) {
            return KindDough.CHOUX_PASTRY;
        } else {
            if (meat.getKindMeat().equals(KindMeat.CHICKEN)) {
                return KindDough.SHORTCRUST_PASTRY;
            } else {
                return KindDough.YEAST_DOUGH;
            }
        }
    }

}
