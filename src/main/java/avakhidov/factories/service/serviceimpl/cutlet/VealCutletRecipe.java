package avakhidov.factories.service.serviceimpl.cutlet;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.stereotype.Service;


import static avakhidov.factories.enums.dough.ParameterDoughEnum.WHEAT_FLOUR_FINE;

@Service
public class VealCutletRecipe implements Recipe<Cutlet<VealMeat>> {

    final
    private MeatServiceImpl meatService;

    public VealCutletRecipe(MeatServiceImpl meatService) {
        this.meatService = meatService;
    }

    @Override
    public Cutlet<VealMeat> cooked(double weight) {

        VealCutlet vealCutlet = new VealCutlet((VealMeat) meatService.buildMeat(VealMeat.class), true, weight);
        vealCutlet.setFinished(Finished.RAW);

        vealCutlet.createSesameBun(WHEAT_FLOUR_FINE, true, new Sesame(), weight * 0.8);

        return vealCutlet;
    }
}
