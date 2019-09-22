package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;

import java.time.LocalTime;

public class WheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(int temperature, LocalTime time) {
        return new WheatBun(ParameterDoughEnum.WHEAT_FLOUR_FINE, true, temperature, time);
    }
}
