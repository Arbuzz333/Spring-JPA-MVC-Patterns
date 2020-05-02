package avakhidov.factories.config;


import avakhidov.factories.cache.CutletCacheEventListener;
import avakhidov.factories.cache.ProductCacheEventListener;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.kitchen.Kitchen;
import avakhidov.factories.service.recipe.Recipe;
import avakhidov.factories.service.orders.OrderVerification;
import avakhidov.factories.service.orders.OrdersMaker;
import avakhidov.factories.service.orders.OrdersSplitter;
import avakhidov.factories.service.recipe.bun.BuckwheatBunRecipe;
import avakhidov.factories.service.recipe.bun.CornBunRecipe;
import avakhidov.factories.service.recipe.bun.WheatBunRecipe;
import avakhidov.factories.service.recipe.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.recipe.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.recipe.cutlet.VealCutletRecipe;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static avakhidov.factories.cache.CacheNamesEnum.CUTLET_EHCACHE;
import static avakhidov.factories.cache.CacheNamesEnum.PRODUCT_EHCACHE;

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
    private final ProductCacheEventListener listener;
    private final CutletCacheEventListener cutletCacheEventListener;

    public AppContext(CornBunRecipe cornBunRecipe
            , BuckwheatBunRecipe buckwheatBunRecipe
            , WheatBunRecipe wheatBunRecipe
            , ChickenCutletRecipe chickenCutletRecipe
            , VealCutletRecipe vealCutletRecipe
            , PorkCutletRecipe porkCutletRecipe
            , CommandOrders commandOrders
            , OrdersSplitter ordersSplitter
            , OrderVerification verification
            , ProductCacheEventListener listener
            , CutletCacheEventListener cutletCacheEventListener) {
        this.cornBunRecipe = cornBunRecipe; this.buckwheatBunRecipe = buckwheatBunRecipe; this.wheatBunRecipe = wheatBunRecipe;
        this.chickenCutletRecipe = chickenCutletRecipe; this.vealCutletRecipe = vealCutletRecipe; this.porkCutletRecipe = porkCutletRecipe;
        this.commandOrders = commandOrders; this.ordersSplitter = ordersSplitter; this.verification = verification; this.listener = listener;
        this.cutletCacheEventListener = cutletCacheEventListener;
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

    @Bean(name = "ehcacheCacheManager")
    public CacheManager ehcacheCacheManager() {

        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(listener, EventType.CREATED, EventType.EXPIRED)
                .unordered().asynchronous();

        CacheConfiguration<Long, ArrayList> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, ArrayList.class,
                        ResourcePoolsBuilder
                                .heap(10))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(2)))
                .withService(cacheEventListenerConfiguration)
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(
                        PRODUCT_EHCACHE.getCode(),
                        cacheConfiguration)
                .build(true);

        return cacheManager;
    }

    @Bean(name = "ehcacheCacheManagerCutlet")
    public CacheManager ehcacheCacheManagerCutlet() {

        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(cutletCacheEventListener, EventType.CREATED, EventType.UPDATED, EventType.EVICTED, EventType.REMOVED)
                .unordered().asynchronous();

        CacheConfiguration<String, Cutlet> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Cutlet.class,
                        ResourcePoolsBuilder
                                .heap(5))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5)))
                .withService(cacheEventListenerConfiguration)
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(
                        CUTLET_EHCACHE.getCode(),
                        cacheConfiguration)
                .build(true);

        return cacheManager;
    }

}
