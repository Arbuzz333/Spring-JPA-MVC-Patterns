package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;


@Service
public class BuckwheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(double weight) {
        ParameterPrepareDough prepareDough =
                ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM.toKneadTheDough();

        return new BuckwheatBun(prepareDough, true, weight);
    }
}
