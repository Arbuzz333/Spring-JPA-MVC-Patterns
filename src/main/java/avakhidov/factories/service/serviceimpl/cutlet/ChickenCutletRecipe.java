package avakhidov.factories.service.serviceimpl.cutlet;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


@Service
public class ChickenCutletRecipe implements Recipe<Cutlet<ChickenMeat>> {

    @Override
    public Cutlet<ChickenMeat> cooked(double weight) {

        ChickenCutlet chickenCutlet = new ChickenCutlet(new ChickenMeat(FatMeat.DIETARY, new Chicken()), true, weight);
        chickenCutlet.setFinished(Finished.RAW);

        chickenCutlet.createSesameBun(
                ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM
                , true
                , new Sesame()
                , weight * 0.75);

        return chickenCutlet;
    }
}
