package avakhidov.factories.cache;

import avakhidov.factories.entity.cutlet.Cutlet;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CutletCacheEventListener implements CacheEventListener<String, Cutlet> {

    private static final Logger LOG = LoggerFactory.getLogger(CutletCacheEventListener.class);

    @Override
    public void onEvent(CacheEvent cacheEvent) {
        LOG.debug("Cache event = {}, Key = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue());
        System.out.println(String.format("Cache event = %s, Key = %s, New value = %s", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue()));
    }
}
