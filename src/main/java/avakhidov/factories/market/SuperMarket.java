package avakhidov.factories.market;

import avakhidov.factories.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class SuperMarket<T extends Product> implements Market<T>, BigMarket<T> {

    private final Logger logger = LoggerFactory.getLogger(SuperMarket.class);


    private T cutlet;
    private BigInteger quantity;

    private static final BigInteger MAX_QUANTITY = BigInteger.valueOf(1555);

    private List<Market> list = new ArrayList<>();

    @Override
    public Map<Product, Integer> orderQuantities() {
        Map<Product, Integer> result = new HashMap<>();
        list.forEach(market -> result.merge(market.getProduct(), market.orderQuantity(),
                Integer::sum));

        return result;
    }

    @Override
    public List<Market> setMarket(Market ... markets) {
        list.addAll(Arrays.asList(markets));
        return list;
    }

    @Override
    public List<Market> getMarketList() {
        return this.list;
    }

    @Override
    public int orderQuantity() {
        final int commonQuantities = list.stream().mapToInt(Market::orderQuantity).sum();
        logger.info("Got commonQuantities {}, quantity {}", commonQuantities, quantity);
        int result = MAX_QUANTITY.subtract(BigInteger.valueOf(commonQuantities)).subtract(quantity).intValue();
        logger.info("Got orderQuantity {}", result);
        return result;
    }

    @Override
    public Market<T> setQuantity(int quantity) {
        if (quantity == 0) {
            this.quantity = BigInteger.ZERO;
        } else {
            this.quantity = BigInteger.valueOf(quantity);
        }
        return this;
    }

    @Override
    public T getProduct() {
        return this.cutlet;
    }

    @Override
    public void setProduct(T cutlet) {
        this.cutlet = cutlet;
    }

    @Override
    public BigInteger getQuantity() {
        return quantity;
    }
}
