package avakhidov.factories.config;


import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.kitchen.Kitchen;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
class AppContext {

    private final static double WEIGHT_CUTLET = 0.125;
    private final static double WEIGHT_BUN = 0.075;

    private final CornBunRecipe cornBunRecipe;
    private final BuckwheatBunRecipe buckwheatBunRecipe;
    private final WheatBunRecipe wheatBunRecipe;

    private final ChickenCutletRecipe chickenCutletRecipe;
    private final VealCutletRecipe vealCutletRecipe;
    private final PorkCutletRecipe porkCutletRecipe;

    public AppContext(CornBunRecipe cornBunRecipe
            , BuckwheatBunRecipe buckwheatBunRecipe
            , WheatBunRecipe wheatBunRecipe
            , ChickenCutletRecipe chickenCutletRecipe
            , VealCutletRecipe vealCutletRecipe
            , PorkCutletRecipe porkCutletRecipe) {
        this.cornBunRecipe = cornBunRecipe; this.buckwheatBunRecipe = buckwheatBunRecipe; this.wheatBunRecipe = wheatBunRecipe;
        this.chickenCutletRecipe = chickenCutletRecipe; this.vealCutletRecipe = vealCutletRecipe; this.porkCutletRecipe = porkCutletRecipe;
    }

    @Bean
    public Kitchen kitchen() {
        Map<MainIngredientEnum, List<Recipe>> enumRecipeMap = new TreeMap<>();
        enumRecipeMap.put(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, Arrays.asList(cornBunRecipe, buckwheatBunRecipe, wheatBunRecipe));
        enumRecipeMap.put(MainIngredientEnum.MEAT, Arrays.asList(chickenCutletRecipe, vealCutletRecipe, porkCutletRecipe));

        Map<MainIngredientEnum, Double> mainIngredientWeight = new TreeMap<>();
        mainIngredientWeight.put(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, WEIGHT_BUN);
        mainIngredientWeight.put(MainIngredientEnum.MEAT, WEIGHT_CUTLET);

        return new Kitchen(enumRecipeMap, mainIngredientWeight);
    }

}
