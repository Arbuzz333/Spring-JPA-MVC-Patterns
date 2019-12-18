package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.enums.dough.KindDough;

public class CornDough extends Dough<CornFlour> {

    public CornDough(CornFlour flour, KindDough kindDough, double fat) {
        super(flour, kindDough, fat);
    }

}
