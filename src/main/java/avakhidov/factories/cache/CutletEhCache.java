package avakhidov.factories.cache;

import avakhidov.factories.annotations.PostProxy;
import avakhidov.factories.entity.cutlet.services.CreateCutletByBuildersEnum;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.stereotype.Service;

import static avakhidov.factories.cache.CacheNamesEnum.CUTLET_EHCACHE;


@Service
public class CutletEhCache {

    private final CacheManager ehcacheCacheManagerCutlet;

    public CutletEhCache(
            CacheManager ehcacheCacheManagerCutlet) {
        this.ehcacheCacheManagerCutlet = ehcacheCacheManagerCutlet;
    }

    @PostProxy
    public Cutlet<? extends Meat> getCutletCacheable(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutlet = getCutletById(id);
        cache.put(name, cutlet);

        return cutlet;
    }

    public Cutlet<? extends Meat> getCutletCache(String name, Long id) {
        Cache<String, Cutlet> cache = ehcacheCacheManagerCutlet.getCache(CUTLET_EHCACHE.getCode(), String.class, Cutlet.class);

        Cutlet<? extends Meat> cutletCache = cache.get(name);
        if (cutletCache == null) {
            cutletCache = getCutletById(id);
        }
        return cutletCache;
    }

    public Cutlet<? extends Meat> getCutletCache(String name) {
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
        Cutlet<? extends Meat> result;
        result = CreateCutletByBuildersEnum.getCreateCutletByBuildersEnumById(id).createCutletByIndex();
        return result;
    }
}
