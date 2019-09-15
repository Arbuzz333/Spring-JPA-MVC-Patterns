package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;

public class CornBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new CornBun(ParameterDoughEnum.CORN_FLOUR_COARSE, true);
    }
}
