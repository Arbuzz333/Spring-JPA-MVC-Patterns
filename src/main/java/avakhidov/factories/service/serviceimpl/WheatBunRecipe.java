package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class WheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(int temperature, LocalTime time, double weight) {
        return new WheatBun(ParameterDoughEnum.WHEAT_FLOUR_FINE, true, temperature, time, weight);
    }
}
