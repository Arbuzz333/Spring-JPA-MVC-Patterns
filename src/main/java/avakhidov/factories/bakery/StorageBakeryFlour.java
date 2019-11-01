package avakhidov.factories.bakery;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.KindFlour;

import java.math.BigDecimal;

public class StorageBakeryFlour implements StorageBakery<Flour> {

    private BigDecimal weight;
    private Flour flour;

    public StorageBakeryFlour(BigDecimal weight, Flour flour) {
        this.weight = weight;
        this.flour = flour;
    }

    private static final int WHEAT_COEFFICIENT = 1;
    private static final double CORN_COEFFICIENT = 0.8;
    private static final double BUCKWHEAT_COEFFICIENT = 0.7;

    @Override
    public BigDecimal stockValue() {
        if (weight == null) {
            this.weight = BigDecimal.ONE;
            return this.weight;
        }
        switch (this.flour.getKind()) {
            case WHEAT:
                return this.weight.multiply(BigDecimal.valueOf(WHEAT_COEFFICIENT));
            case CORN:
                return this.weight.multiply(BigDecimal.valueOf(CORN_COEFFICIENT));
            case BUCKWHEAT:
                return this.weight.multiply(BigDecimal.valueOf(BUCKWHEAT_COEFFICIENT));
        }
        return this.weight;
    }

    @Override
    public KindFlour getKindFlour() {
        return this.flour.getKind();
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

}
