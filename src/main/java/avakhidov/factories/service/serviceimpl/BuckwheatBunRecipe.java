package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;

public class BuckwheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new BuckwheatBun(ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM, true);
    }
}
