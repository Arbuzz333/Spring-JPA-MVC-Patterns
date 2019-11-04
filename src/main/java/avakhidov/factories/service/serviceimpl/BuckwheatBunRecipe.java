package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BuckwheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(int temperature, LocalTime time) {
        return new BuckwheatBun(ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM, true, temperature, time);
    }
}
