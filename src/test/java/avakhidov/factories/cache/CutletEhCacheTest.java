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

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CutletEhCacheTest {

    @Autowired
    private CutletEhCache cutletEhCache;

    @Test
    public void getCutletCacheable() {

        Cutlet<? extends Meat> firstCutlet = cutletEhCache.getCutletCacheable("FirstCutlet", 1L);
        assertEquals("", KindMeat.MUTTON, firstCutlet.getMainIngredient().getKindMeat());
        assertEquals("", KindDough.YEAST_DOUGH, firstCutlet.getSesameBun().getMainIngredient().getKindDough());

        Cutlet<? extends Meat> secondCutlet = cutletEhCache.getCutletCach("FirstCutlet", 2L);
        assertEquals("", KindMeat.MUTTON, secondCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletReplace = cutletEhCache.getCutletCacheReplace("FirstCutlet", 2L);
        assertEquals("", KindMeat.PORK, firstCutletReplace.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> thirdCutlet = cutletEhCache.getCutletCacheClearPut("FirstCutlet", 3L);
        assertEquals("", KindMeat.CHICKEN, thirdCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> fourCutlet = cutletEhCache.getCutletCachePutIfAbsent("FirstCutlet", 4L);
        assertEquals("", KindMeat.CHICKEN, fourCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> fourCutlet2 = cutletEhCache.getCutletCachePutIfAbsent("FourCutlet", 4L);
        assertEquals("", KindMeat.VEAL, fourCutlet2.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> FiveCutlet = cutletEhCache.getCutletCachePutIfAbsent("FiveCutlet", 1L);
        assertEquals("", KindMeat.MUTTON, FiveCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SixCutlet = cutletEhCache.getCutletCachePutIfAbsent("SixCutlet", 3L);
        assertEquals("", KindMeat.CHICKEN, SixCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> SevenCutlet = cutletEhCache.getCutletCachePutIfAbsent("SevenCutlet", 4L);
        assertEquals("", KindMeat.VEAL, SevenCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> EightCutlet = cutletEhCache.getCutletCachePutIfAbsent("EightCutlet", 1L);
        assertEquals("", KindMeat.MUTTON, EightCutlet.getMainIngredient().getKindMeat());

        Cutlet<? extends Meat> firstCutletEvict = cutletEhCache.getCutletCach("FirstCutlet");
        assertEquals("", null, firstCutletEvict);

    }
}