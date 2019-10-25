package avakhidov.factories.bakery;

import avakhidov.factories.entity.flour.Flour;

import java.math.BigDecimal;

public class StorageBakeryFlour implements StorageBakery<Flour> {

    private BigDecimal weight;


    @Override
    public BigDecimal stockValue(Flour stock) {
        if (weight == null) {
            this.weight = BigDecimal.ONE;
        }
        return this.weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

}
