package avakhidov.factories.cache;

import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PancakeCacheTest {

    @Autowired
    PancakeCache pancakeCache;

    @Test
    public void getPancakePutEhcache() {
        PancakeBuckwheat pancakePutEhcache = (PancakeBuckwheat) pancakeCache.getPancakePutEhcache(KindFlour.BUCKWHEAT);

        assertEquals("", KindDough.PANCAKE, pancakePutEhcache.getMainIngredient().getKindDough());
        assertEquals("", GrindingFlour.MEDIUM, pancakePutEhcache.getMainIngredient().getFlour().getGrinding());

        PancakeBuckwheat pancakeEhcache = (PancakeBuckwheat) pancakeCache.getPancakeFromEhcache(KindFlour.BUCKWHEAT);

        assertEquals("", KindDough.PANCAKE, pancakeEhcache.getMainIngredient().getKindDough());
        assertEquals("", GrindingFlour.MEDIUM, pancakeEhcache.getMainIngredient().getFlour().getGrinding());

        pancakeCache.removeFromCache(KindFlour.BUCKWHEAT);

        PancakeBuckwheat pancakeRemove = (PancakeBuckwheat) pancakeCache.getPancakeFromEhcache(KindFlour.BUCKWHEAT);
        assertNull("", pancakeRemove);
    }

}