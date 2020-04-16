package avakhidov.factories.cache;

import avakhidov.factories.entity.Product;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ehcache.CacheManager;
import org.ehcache.Cache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static avakhidov.factories.cache.CacheNamesEnum.PRODUCT_EHCACHE;

@Component
public class ProductMakerEhCache {

    private static final Logger logger = LoggerFactory.getLogger(ProductMakerEhCache.class);

    private List<Product> productList = new ArrayList<>();


    private final CacheManager ehcacheCacheManager;

    private final OrdersMakerProduct visitor;


    public ProductMakerEhCache(
            OrdersMakerProduct visitor,
            CacheManager ehcacheCacheManager) {
        this.visitor = visitor;
        this.ehcacheCacheManager = ehcacheCacheManager;
    }


    public List<Product> getProductListEhcache(Class<? extends Product> clazz, int quantity) {
        Cache<Long, ArrayList> cache = ehcacheCacheManager.getCache(PRODUCT_EHCACHE.getCode(), Long.class, ArrayList.class);
        List<Product> result = new ArrayList<>();

        visitor.initOrdersMakerProduct(quantity, clazz);
        try {
            result = visitor.accept();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (cache != null) {
            cache.put(1L, (ArrayList) result);
        } else {
            logger.error("Cache {} is null", PRODUCT_EHCACHE.getCode());
        }
        productList = result;
        return result;
    }

    public List<Product> getProductListEhcache() {
        List<Product> result = new ArrayList<>();

        Cache<Long, ArrayList> cache = ehcacheCacheManager.getCache("productEhcache", Long.class, ArrayList.class);
        if (cache != null) {
            result = (List<Product>) cache.get(1L);
        }
        if (result == null) {
            logger.warn("Cache {} is empty", PRODUCT_EHCACHE.getCode());
            result = productList;
        }
        return result;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
