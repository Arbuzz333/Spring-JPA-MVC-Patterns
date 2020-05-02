package avakhidov.factories.config;

import avakhidov.factories.entity.Product;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ProductPersistentCacheEventListener implements CacheEventListener<String, Product> {

    private static final Logger LOG = LoggerFactory.getLogger(ProductPersistentCacheEventListener.class);

    @Override
    public void onEvent(CacheEvent cacheEvent) {
        LOG.debug("Cache event = {}, Key = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue());
        System.out.println(String.format("Cache event = %s, Key = %s, New value = %s, Old value = %s",
                cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue(), cacheEvent.getOldValue()));
    }
}
