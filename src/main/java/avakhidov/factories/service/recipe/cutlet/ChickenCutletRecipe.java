package avakhidov.factories.service.recipe.cutlet;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.recipe.Recipe;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.stereotype.Service;



@Service
public class ChickenCutletRecipe implements Recipe<Cutlet<ChickenMeat>> {

    final
    private MeatServiceImpl meatService;

    public ChickenCutletRecipe(MeatServiceImpl meatService) {
        this.meatService = meatService;
    }

    @Override
    public Cutlet<ChickenMeat> cooked(double weight) {

        ChickenCutlet chickenCutlet = new ChickenCutlet((ChickenMeat) meatService.buildMeat(ChickenMeat.class), true, weight);
        chickenCutlet.setFinished(Finished.RAW);

        chickenCutlet.createSesameBun(
                ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM
                , true
                , new Sesame()
                , weight * 0.75);

        return chickenCutlet;
    }
}
