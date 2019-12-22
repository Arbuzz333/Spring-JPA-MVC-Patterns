package avakhidov.factories.market;

import avakhidov.factories.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HyperMarket<T extends Product> implements Market<T>, BigMarket<T> {

    private final Logger logger = LoggerFactory.getLogger(HyperMarket.class);

    private static final BigInteger MAX_QUANTITY = BigInteger.valueOf(5555);

    private T product;
    private BigInteger quantity = BigInteger.ZERO;
    private List<SuperMarket> superMarkets = new ArrayList<>();
    private EditorHyperMarket editorHyperMarket = new EditorHyperMarket();

    @Override
    public Map<Product, Integer> orderQuantities() {
        Map<Product, Integer> result = new HashMap<>();
        superMarkets.forEach(superMarket -> result.merge(superMarket.getProduct(), superMarket.getQuantity().intValue(),
                Integer::sum));
        superMarkets.forEach(superMarket -> superMarket.getMarketList()
                .forEach(market -> result.merge(((Market) market).getProduct(), ((Market) market).getQuantity().intValue(), Integer::sum)));
        result.merge(product, quantity.intValue(), Integer:: sum);

        return result;
    }

    @Override
    public List<Market> setMarket(Market ...markets) {
        for (Market market : markets) {
            SuperMarket min = Collections.min(superMarkets, (s1, s2) -> s2.orderQuantity() - s1.orderQuantity());
            min.setMarket(market);
        }
        return getMarketList();
    }

    @Override
    public List<Market> getMarketList() {
        List<Market> result = new ArrayList<>();
        superMarkets.forEach(superMarket -> result.addAll(superMarket.getMarketList()));
        return result;
    }

    @Override
    public int orderQuantity() {
        final int commonQuantities = superMarkets.stream().mapToInt(SuperMarket::orderQuantity).sum();
        logger.info("Got commonQuantities {}", commonQuantities);
        return MAX_QUANTITY.subtract(BigInteger.valueOf(commonQuantities)).subtract(quantity).intValue();
    }

    @Override
    public Market<T> setQuantity(int quantity) {
        if (quantity == 0) {
            this.quantity = BigInteger.ZERO;
        } else {
            this.quantity = this.quantity.add(BigInteger.valueOf(quantity));
        }
        return this;
    }

    @Override
    public T getProduct() {
        return this.product;
    }

    @Override
    public BigInteger getQuantity() {
        return quantity;
    }

    @Override
    public void setProduct(T cutlet) {
        this.product = cutlet;
    }

    public List<SuperMarket> getSuperMarkets() {
        return superMarkets;
    }

    public void reduceQuantity(int coefficient) {
        editorHyperMarket.reduce(coefficient);
    }

    private class EditorHyperMarket {

        void reduce(int coefficient) {
            quantity = reduceFromPercent(quantity, coefficient);
            superMarkets.forEach(superMarket -> superMarket.getMarketList().forEach(o ->
                    ((Market) o).setQuantity(reduceFromPercent(((Market) o).getQuantity(), coefficient).intValue()))
            );
        }

        private BigInteger reduceFromPercent(BigInteger value, int coefficient) {
            return value.subtract(
                    value.multiply(BigInteger.valueOf(coefficient)).divide(BigInteger.valueOf(100)));
        }
    }
}
