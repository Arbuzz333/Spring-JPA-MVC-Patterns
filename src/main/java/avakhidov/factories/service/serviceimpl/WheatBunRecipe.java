package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;

public class WheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new WheatBun(ParameterDoughEnum.WHEAT_FLOUR_FINE, true);
    }
}
