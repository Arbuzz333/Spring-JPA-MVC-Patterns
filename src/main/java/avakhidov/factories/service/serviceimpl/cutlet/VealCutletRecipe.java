package avakhidov.factories.service.serviceimpl.cutlet;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static avakhidov.factories.enums.dough.ParameterDoughEnum.WHEAT_FLOUR_FINE;

@Service
public class VealCutletRecipe implements Recipe<Cutlet<VealMeat>> {

    @Override
    public Cutlet<VealMeat> cooked(int temperature, LocalTime time, double weight) {

        VealCutlet vealCutlet = new VealCutlet(new VealMeat(FatMeat.DIETARY, new Calf()), true, weight);
        vealCutlet.setFinished(Finished.RAW);

        vealCutlet.createSesameBun(WHEAT_FLOUR_FINE, true, new Sesame(), temperature, time, weight * 0.8);

        return vealCutlet;
    }
}
