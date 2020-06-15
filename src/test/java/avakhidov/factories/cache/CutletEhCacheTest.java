package avakhidov.factories.cache;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static avakhidov.factories.cache.CacheParameters.EIGHT_CUTLET;
import static avakhidov.factories.cache.CacheParameters.FIRST_CUTLET;
import static avakhidov.factories.cache.CacheParameters.FIVE_CUTLET;
import static avakhidov.factories.cache.CacheParameters.FOUR_CUTLET;
import static avakhidov.factories.cache.CacheParameters.SEVEN_CUTLET;
import static avakhidov.factories.cache.CacheParameters.SIX_CUTLET;
import static avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum.CREATE_CHICKEN_CUTLET;
import static avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum.CREATE_MUTTON_CUTLET;
import static avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum.CREATE_PORK_CUTLET;
import static avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum.CREATE_VEAL_CUTLET;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletEhCacheTest {

    @Autowired
    private CutletEhCache cutletEhCache;

    @Test
    public void getCutletCacheable() {

        Cutlet<? extends Meat> firstCutlet = cutletEhCache.getCutletCach(FIRST_CUTLET.getName());
        assertEquals("", KindMeat.MUTTON, firstCutlet.getMainIngredient().getKindMeat());
        assertEquals("", KindDough.YEAST_DOUGH, firstCutlet.getSesameBun().getMainIngredient().getKindDough());

        Cutlet<? extends Meat> secondCutlet = cutletEhCache.getCutletCach(FIRST_CUTLET.getName(), CREATE_PORK_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, secondCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletReplace = cutletEhCache.getCutletCacheReplace(FIRST_CUTLET.getName(), CREATE_PORK_CUTLET.getId());
        assertEquals("", KindMeat.PORK, firstCutletReplace.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> thirdCutlet = cutletEhCache.getCutletCacheClearPut(FIRST_CUTLET.getName(), CREATE_CHICKEN_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, thirdCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> fourCutlet = cutletEhCache.getCutletCachePutIfAbsent(FIRST_CUTLET.getName(), CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, fourCutlet.getMainIngredient().getKindMeat());
        assertEquals("", 0.375, fourCutlet.getWeight());

        Cutlet<? extends Meat> fourCutlet2 = cutletEhCache.getCutletCachePutIfAbsent(FOUR_CUTLET.getName(), CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.VEAL, fourCutlet2.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> FiveCutlet = cutletEhCache.getCutletCachePutIfAbsent(FIVE_CUTLET.getName(), CREATE_MUTTON_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, FiveCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SixCutlet = cutletEhCache.getCutletCachePutIfAbsent(SIX_CUTLET.getName(), CREATE_CHICKEN_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, SixCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SevenCutlet = cutletEhCache.getCutletCachePutIfAbsent(SEVEN_CUTLET.getName(), CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.VEAL, SevenCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> EightCutlet = cutletEhCache.getCutletCachePutIfAbsent(EIGHT_CUTLET.getName(), CREATE_MUTTON_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, EightCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletEvict = cutletEhCache.getCutletCach(FIRST_CUTLET.getName());
        Cutlet<? extends Meat> fourCutletEvict = cutletEhCache.getCutletCach(FOUR_CUTLET.getName());
        Cutlet<? extends Meat> fiveCutletEvict = cutletEhCache.getCutletCach(FIVE_CUTLET.getName());
        Cutlet<? extends Meat> sixCutletEvict = cutletEhCache.getCutletCach(SIX_CUTLET.getName());

        boolean flag = firstCutletEvict == null || fourCutletEvict == null || fiveCutletEvict == null || sixCutletEvict == null;
        assertTrue("Flag is false", flag);

    }
}