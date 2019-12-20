package avakhidov.factories.market;

import avakhidov.factories.entity.Product;

import java.util.List;
import java.util.Map;

public interface BigMarket<T extends Product> {

    Map<Product, Integer> orderQuantities();

    List<Market> setMarket(Market ... markets);

    void setProduct(T cutlet);

    List<Market> getMarketList();
}
