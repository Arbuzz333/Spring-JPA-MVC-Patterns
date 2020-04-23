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

import static avakhidov.factories.cache.services.CreateCutletByBuildersEnum.CREATE_CHICKEN_CUTLET;
import static avakhidov.factories.cache.services.CreateCutletByBuildersEnum.CREATE_MUTTON_CUTLET;
import static avakhidov.factories.cache.services.CreateCutletByBuildersEnum.CREATE_PORK_CUTLET;
import static avakhidov.factories.cache.services.CreateCutletByBuildersEnum.CREATE_VEAL_CUTLET;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletEhCacheTest {

    @Autowired
    private CutletEhCache cutletEhCache;

    @Test
    public void getCutletCacheable() {

        Cutlet<? extends Meat> firstCutlet = cutletEhCache.getCutletCacheable("FirstCutlet", CREATE_MUTTON_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, firstCutlet.getMainIngredient().getKindMeat());
        assertEquals("", KindDough.YEAST_DOUGH, firstCutlet.getSesameBun().getMainIngredient().getKindDough());

        Cutlet<? extends Meat> secondCutlet = cutletEhCache.getCutletCach("FirstCutlet", CREATE_PORK_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, secondCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletReplace = cutletEhCache.getCutletCacheReplace("FirstCutlet", CREATE_PORK_CUTLET.getId());
        assertEquals("", KindMeat.PORK, firstCutletReplace.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> thirdCutlet = cutletEhCache.getCutletCacheClearPut("FirstCutlet", CREATE_CHICKEN_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, thirdCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> fourCutlet = cutletEhCache.getCutletCachePutIfAbsent("FirstCutlet", CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, fourCutlet.getMainIngredient().getKindMeat());
        assertEquals("", 0.375, fourCutlet.getWeight());

        Cutlet<? extends Meat> fourCutlet2 = cutletEhCache.getCutletCachePutIfAbsent("FourCutlet", CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.VEAL, fourCutlet2.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> FiveCutlet = cutletEhCache.getCutletCachePutIfAbsent("FiveCutlet", CREATE_MUTTON_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, FiveCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SixCutlet = cutletEhCache.getCutletCachePutIfAbsent("SixCutlet", CREATE_CHICKEN_CUTLET.getId());
        assertEquals("", KindMeat.CHICKEN, SixCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SevenCutlet = cutletEhCache.getCutletCachePutIfAbsent("SevenCutlet", CREATE_VEAL_CUTLET.getId());
        assertEquals("", KindMeat.VEAL, SevenCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> EightCutlet = cutletEhCache.getCutletCachePutIfAbsent("EightCutlet", CREATE_MUTTON_CUTLET.getId());
        assertEquals("", KindMeat.MUTTON, EightCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletEvict = cutletEhCache.getCutletCach("FirstCutlet");
        assertEquals("", null, firstCutletEvict);

    }
}