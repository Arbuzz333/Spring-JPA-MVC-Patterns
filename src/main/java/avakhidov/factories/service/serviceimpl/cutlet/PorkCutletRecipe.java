package avakhidov.factories.service.serviceimpl.cutlet;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


@Service
public class PorkCutletRecipe implements Recipe<Cutlet<PorkMeat>> {

    @Override
    public PorkCutlet cooked(double weight) {

        PorkCutlet porkCutlet = new PorkCutlet(new PorkMeat(FatMeat.MEDIUMFAT, new Pig()), true, weight);
        porkCutlet.setFinished(Finished.RAW);

        porkCutlet.createSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE
                , true, new Sesame()
                , weight * 0.75);

        return porkCutlet;
    }
}
