package avakhidov.factories.cache;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.MuttonCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static avakhidov.factories.cache.CacheNamesEnum.CUTLET_EHCACHE;
import static avakhidov.factories.enums.dough.ParameterDoughEnum.CORN_FLOUR_COARSE;
import static avakhidov.factories.enums.dough.ParameterDoughEnum.WHEAT_FLOUR_FINE;


@Service
public class CutletEhCache {

    @Value("#{new Double('${coefficient_buckwheat}')}")
    private double coefficient_buckwheat;

    @Value("#{new Double('${coefficient_wheat}')}")
    private double coefficient_wheat;

    @Value("#{new Double('${weight_mutton_cutlet}')}")
    private double weightMuttonCutlet;

    @Value("#{new Double('${weight_pork_cutlet}')}")
    private double weightPorkCutlet;

    @Value("#{new Double('${weight_chicken_cutlet}')}")
    private double weightChickenCutlet;

    private final MeatServiceImpl meatService;
    private final CacheManager ehcacheCacheManagerCutlet;

    public CutletEhCache(
            MeatServiceImpl meatService,
            CacheManager ehcacheCacheManagerCutlet) {
        this.meatService = meatService;
        this.ehcacheCacheManagerCutlet = ehcacheCacheManagerCutlet;
    }

    public Cutlet<? extends Meat> getCutletCacheable(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutlet = getCutletById(id);
        cache.put(name, cutlet);

        return cutlet;
    }

    public Cutlet<? extends Meat> getCutletCach(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutletCache = cache.get(name);
        if (cutletCache == null) {
            cutletCache = getCutletById(id);
        }
        return cutletCache;
    }

    public Cutlet<? extends Meat> getCutletCach(String name) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutletCache = cache.get(name);
        return cutletCache;
    }

    public Cutlet<? extends Meat> getCutletCacheReplace(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutlet = getCutletById(id);
        cache.replace(name, cutlet);

        return cache.get(name);
    }

    public Cutlet<? extends Meat> getCutletCacheClearPut(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        cache.remove(name);
        Cutlet<? extends Meat> cutlet = getCutletById(id);
        cache.put(name, cutlet);

        return cache.get(name);
    }

    public Cutlet<? extends Meat> getCutletCachePutIfAbsent(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutlet = getCutletById(id);
        cache.putIfAbsent(name, cutlet);

        return cache.get(name);
    }


    private Cutlet<? extends Meat> getCutletById(Long id) {
        Cutlet<? extends Meat> result = null;
        if (id == 1) {
            result = MuttonCutlet.builderMuttonCutlet()
                    .withMainIngredient(FatMeat.SPECK)
                    .withRecipeReady(true)
                    .withFinished(Finished.RAW)
                    .withWeight(weightMuttonCutlet)
                    .build();
        }
        if (id == 2) {
            result = PorkCutlet.builderPorkCutlet()
                    .withMainIngredient(FatMeat.MEDIUMFAT)
                    .withRecipeReady(true)
                    .withFinished(Finished.RAW)
                    .withWeight(weightPorkCutlet)
                    .withSeasamBun(coefficient_buckwheat * weightPorkCutlet)
                    .build();
        }
        if (id == 3) {
            result = ChickenCutlet.outerBuilderChickenCutlet()
                    .withMainIngredient(FatMeat.DIETARY)
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withSesameBun(CORN_FLOUR_COARSE, new Sesame())
                    .withKindDough(KindDough.PUFF_PASTRY)
                    .withWeight(weightChickenCutlet)
                    .build();
        }
        if (id == 4) {
            result = VealCutlet.builderVealCutlet()
                    .withMainIngredient((VealMeat)meatService.buildMeat(VealMeat.class))
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withWeight(weightChickenCutlet)
                    .build();
            Cutlet<? extends Meat>.SesameBun SesameBun = result.builderSesameBun()
                    .withMainIngredient(WHEAT_FLOUR_FINE.toKneadTheDough())
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withKindDough(KindDough.SHORTCRUST_PASTRY)
                    .withWeight(weightChickenCutlet * coefficient_wheat)
                    .build();
        }
        return result;
    }
}
