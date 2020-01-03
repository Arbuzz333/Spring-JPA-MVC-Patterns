package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.enums.dough.KindDough;

public class WheatDough extends Dough<WheatFlour> {

    public WheatDough(WheatFlour flour, KindDough kindDough, double fat) {
        super(flour, kindDough, fat);
    }

}
