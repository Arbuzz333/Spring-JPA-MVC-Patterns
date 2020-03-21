package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.MainIngredient;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class PancakeMakerCache {

    private final OrdersMakerProduct visitor;

    public List<Product> products = new ArrayList<>();

    public PancakeMakerCache(
            OrdersMakerProduct visitor) {
        this.visitor = visitor;
    }


    public void initProductMaker(Class<?> clazz, int quantity) {
        visitor.initOrdersMakerProduct(quantity, clazz);
        try {
            products.clear();
            products.addAll(visitor.accept());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @CacheEvict(value = "productList", allEntries = true)
    public List<Product<? extends MainIngredient>> makeProductListEvict(MainIngredientEnum flour) {
        List<Product<? extends MainIngredient>> productList = null;
        if (products != null) {
            productList = products
                    .stream()
                    .map(product1 -> (Product<? extends MainIngredient>)product1)
                    .filter(p -> p.getMainIngredient().getMainIngredient().equals(flour))
                    .collect(Collectors.toList());
        }
        return productList;
    }

    @CachePut(cacheNames = "product")
    public Product<? extends MainIngredient> makePancakeCachePut(MainIngredientEnum flour) {
        Product<? extends MainIngredient> product = null;
        if (products != null) {
            product = products
                    .stream()
                    .map(product1 -> (Product<? extends MainIngredient>)product1)
                    .filter(p -> p.getMainIngredient().getMainIngredient().equals(flour))
                    .findFirst()
                    .orElse(null);
        }
        return product;
    }

    @Cacheable(cacheNames="product", cacheManager = "ConcurrentMapCacheManager")
    public Product<? extends MainIngredient> makePancake(MainIngredientEnum flour) {
        Product<? extends MainIngredient> product = null;
        if (products != null) {
            product = products
                    .stream()
                    .map(product1 -> (Product<? extends MainIngredient>)product1)
                    .filter(p -> p.getMainIngredient().getMainIngredient().equals(flour))
                    .findFirst()
                    .orElse(null);
        }
        return product;
    }

    @CachePut(cacheNames="productList")
    public List<Product<? extends MainIngredient>> makePancakeListCachePut(MainIngredientEnum flour) {
        List<Product<? extends MainIngredient>> productList = null;
        if (products != null) {
            productList = products
                    .stream()
                    .map(product1 -> (Product<? extends MainIngredient>)product1)
                    .filter(p -> p.getMainIngredient().getMainIngredient().equals(flour))
                    .collect(Collectors.toList());
        }
        return productList;
    }

    @Cacheable(cacheNames={"productList", "bunList"}, cacheManager = "ConcurrentMapCacheManager")
    public List<Product<? extends MainIngredient>> makeProductList(MainIngredientEnum flour) {
        List<Product<? extends MainIngredient>> productList = null;
        if (products != null) {
            productList = products
                    .stream()
                    .map(product1 -> (Product<? extends MainIngredient>)product1)
                    .filter(p -> p.getMainIngredient().getMainIngredient().equals(flour))
                    .collect(Collectors.toList());
        }
        return productList;
    }

    public List<Product> getProduct() {
        return products;
    }
}
