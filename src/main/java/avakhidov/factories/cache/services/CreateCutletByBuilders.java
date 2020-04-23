package avakhidov.factories.cache.services;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;


import static avakhidov.factories.enums.dough.ParameterDoughEnum.CORN_FLOUR_COARSE;

public interface CreateCutletByBuilders {

    default Cutlet<? extends Meat> createCutletByIndex() {

        ChickenCutlet result;

        result = ChickenCutlet.outerBuilderChickenCutlet()
                .withMainIngredient(FatMeat.DIETARY)
                .withFinished(Finished.RAW)
                .withRecipeReady(true)
                .withSesameBun(CORN_FLOUR_COARSE, new Sesame())
                .withKindDough(KindDough.PUFF_PASTRY)
                .withWeight(CreateCutletByBuildersEnum.getWeightChickenCutlet())
                .build();

        return result;
    }

}


