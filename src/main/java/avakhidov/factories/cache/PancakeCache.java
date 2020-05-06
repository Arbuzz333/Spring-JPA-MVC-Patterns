package avakhidov.factories.cache;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.engine.RilePancakeEngine;
import avakhidov.factories.enums.KindFlour;
import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PancakeCache {

    @Autowired
    private PersistentCacheManager ehCachePersistentDiskCacheManager;

    @Autowired
    private RilePancakeEngine engine;

    private Pancake<? extends Flour> pancake;

    public Pancake<? extends Flour> getPancakePutEhcache(KindFlour kindFlour) {
        Cache<String, Product> cache = ehCachePersistentDiskCacheManager.getCache(CacheNamesEnum.TRIES.getCode(), String.class, Product.class);

        Pancake<? extends Flour> process = engine.process(kindFlour);
        cache.put(kindFlour.getTitle(), process);
        pancake = process;

        return process;
    }

    public Pancake<? extends Flour> getPancakeFromEhcache(KindFlour kindFlour) {
        Cache<String, Product> cache = ehCachePersistentDiskCacheManager.getCache(CacheNamesEnum.TRIES.getCode(), String.class, Product.class);

        Product product = cache.get(kindFlour.getTitle());
        return (Pancake<? extends Flour>) product;
    }

    public void removeFromCache(KindFlour kindFlour) {
        Cache<String, Product> cache = ehCachePersistentDiskCacheManager.getCache(CacheNamesEnum.TRIES.getCode(), String.class, Product.class);

        cache.remove(kindFlour.getTitle());
    }

    public Pancake<? extends Flour> getPancake() {
        return pancake;
    }
}
