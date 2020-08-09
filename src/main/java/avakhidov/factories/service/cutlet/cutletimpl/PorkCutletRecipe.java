package avakhidov.factories.service.cutlet.cutletimpl;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.stereotype.Service;



@Service
public class PorkCutletRecipe implements Recipe<Cutlet<PorkMeat>> {

    final
    private MeatServiceImpl meatService;

    public PorkCutletRecipe(MeatServiceImpl meatService) {
        this.meatService = meatService;
    }

    @Override
    public PorkCutlet cooked(double weight) {

        PorkCutlet porkCutlet = new PorkCutlet((PorkMeat) meatService.buildMeat(PorkMeat.class), true, weight);
        porkCutlet.setFinished(Finished.RAW);

        porkCutlet.createSesameBun(ParameterDoughEnum.CORN_FLOUR_COARSE
                , true, new Sesame()
                , weight * 0.75);

        return porkCutlet;
    }
}
