package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;


@Service
public class WheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(double weight) {
        ParameterPrepareDough prepareDough =
                ParameterDoughEnum.WHEAT_FLOUR_FINE.toKneadTheDough();

        return new WheatBun(prepareDough, true, weight);
    }
}
