package avakhidov.factories.config;



import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.cache.ProductMakerCachePrototype;

import avakhidov.factories.service.orders.ordersvisitor.OrderMakerProductVisitor;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Configuration
class AppContextProductMaker {

    private final OrderMakerProductVisitor makerBun;

    private final OrderMakerProductVisitor makerCutlet;

    private final OrderMakerProductVisitor makerPancake;

    public AppContextProductMaker(
            @Qualifier("orderMakerBunVisitor") OrderMakerProductVisitor makerBun,
            @Qualifier("orderMakerCutletVisitor") OrderMakerProductVisitor makerCutlet,
            @Qualifier("orderMakerPancakeVisitor") OrderMakerProductVisitor makerPancake) {
        this.makerBun = makerBun;
        this.makerCutlet = makerCutlet;
        this.makerPancake = makerPancake;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public OrdersMakerProduct getVisitor() {
        return new OrdersMakerProduct(makerBun, makerCutlet, makerPancake);
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public ProductMakerCache productMakerCache() {
        return new ProductMakerCache(getVisitor());
    }

    @Bean
    public ProductMakerCachePrototype ProductMakerCachePrototype() {
        return new ProductMakerCachePrototype() {
            @Override
            public ProductMakerCache getProductMakerCache() {
                return productMakerCache();
            }
        };
    }

}
