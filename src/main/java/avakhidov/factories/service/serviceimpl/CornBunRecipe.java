package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class CornBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(int temperature, LocalTime time, double weight) {
        return new CornBun(ParameterDoughEnum.CORN_FLOUR_COARSE, true, temperature, time, weight);
    }
}
