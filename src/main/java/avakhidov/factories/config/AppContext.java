package avakhidov.factories.config;


import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.kitchen.Kitchen;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.orders.OrderVerification;
import avakhidov.factories.service.orders.OrdersMaker;
import avakhidov.factories.service.orders.OrdersSplitter;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
@PropertySource("classpath:value.properties")
class AppContext {

    private final static double WEIGHT_CUTLET = 0.125;
    private final static double WEIGHT_BUN = 0.075;

    private final CornBunRecipe cornBunRecipe;
    private final BuckwheatBunRecipe buckwheatBunRecipe;
    private final WheatBunRecipe wheatBunRecipe;

    private final ChickenCutletRecipe chickenCutletRecipe;
    private final VealCutletRecipe vealCutletRecipe;
    private final PorkCutletRecipe porkCutletRecipe;

    private final CommandOrders commandOrders;
    private final OrdersSplitter ordersSplitter;
    private final OrderVerification verification;

    public AppContext(CornBunRecipe cornBunRecipe
            , BuckwheatBunRecipe buckwheatBunRecipe
            , WheatBunRecipe wheatBunRecipe
            , ChickenCutletRecipe chickenCutletRecipe
            , VealCutletRecipe vealCutletRecipe
            , PorkCutletRecipe porkCutletRecipe
            , CommandOrders commandOrders
            , OrdersSplitter ordersSplitter
            , OrderVerification verification) {
        this.cornBunRecipe = cornBunRecipe; this.buckwheatBunRecipe = buckwheatBunRecipe; this.wheatBunRecipe = wheatBunRecipe;
        this.chickenCutletRecipe = chickenCutletRecipe; this.vealCutletRecipe = vealCutletRecipe; this.porkCutletRecipe = porkCutletRecipe;
        this.commandOrders = commandOrders; this.ordersSplitter = ordersSplitter; this.verification = verification;
    }

    @Bean
    @Lazy
    public Kitchen kitchen() {
        Map<MainIngredientEnum, List<Recipe>> enumRecipeMap = new TreeMap<>();
        enumRecipeMap.put(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, Arrays.asList(cornBunRecipe, buckwheatBunRecipe, wheatBunRecipe));
        enumRecipeMap.put(MainIngredientEnum.MEAT, Arrays.asList(chickenCutletRecipe, vealCutletRecipe, porkCutletRecipe));

        Map<MainIngredientEnum, Double> mainIngredientWeight = new TreeMap<>();
        mainIngredientWeight.put(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, WEIGHT_BUN);
        mainIngredientWeight.put(MainIngredientEnum.MEAT, WEIGHT_CUTLET);

        return new Kitchen(enumRecipeMap, mainIngredientWeight);
    }

    @Bean(initMethod = "initMapClassMethodHandler")
    @Lazy
    public OrdersMaker ordersMaker() {
        return new OrdersMaker(commandOrders, ordersSplitter, verification);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
