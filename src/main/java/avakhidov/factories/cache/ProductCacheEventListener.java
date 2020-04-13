package avakhidov.factories.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductCacheEventListener implements CacheEventListener<Long, ArrayList> {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCacheEventListener.class);

    @Override
    public void onEvent(CacheEvent cacheEvent) {
        LOG.debug("Cache event = {}, Key = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue());
        System.out.println(String.format("Cache event = %s, Key = %s, New value = %s", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue()));
    }
}
