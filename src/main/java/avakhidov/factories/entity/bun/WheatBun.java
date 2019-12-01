package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.enums.dough.DoughUtil;

import java.time.LocalTime;

public class WheatBun extends Bun {

    private LocalTime localTime;

    public WheatBun(ParameterPrepareDough<WheatFlour> prepareDough, boolean recipeReady, double weight) {
        super(prepareDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        if (localTime == null) {
            localTime = LocalTime.now();
        }
        super.getMainIngredient().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(localTime)
        );
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
