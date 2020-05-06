package avakhidov.factories.config;

import avakhidov.factories.cache.CacheNamesEnum;
import avakhidov.factories.entity.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.impl.config.store.disk.OffHeapDiskStoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class CacheConfiguration extends CachingConfigurerSupport {

    private static final Logger log = LogManager.getLogger(CacheConfiguration.class);

    @Autowired
    private ProductPersistentCacheEventListener listener;

    @Bean
    public PersistentCacheManager ehCachePersistentDiskCacheManager() {

        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(listener, EventType.CREATED, EventType.UPDATED, EventType.EVICTED, EventType.REMOVED)
                .unordered().asynchronous();

        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(getStoragePath(), "productPersistence")))
                .withCache(CacheNamesEnum.TRIES.getCode(), CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Product.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, MemoryUnit.KB)
                                .offheap(5, MemoryUnit.MB)
                                .disk(25, MemoryUnit.MB, true)
                        )
                        .withSizeOfMaxObjectGraph(10)
                        .withSizeOfMaxObjectSize(100, MemoryUnit.B)
                        .withService(cacheEventListenerConfiguration)
                        .withService(new OffHeapDiskStoreConfiguration(4))
                ).build(true);

        return persistentCacheManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    private String getStoragePath() {
        String location = "E:" + File.separator + "temp" + File.separator + "ehcache";
        return location;
    }
}
