package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;

import java.util.Map;

public class HoldOven<T> implements Oven<T> {

    private static final int DEFAULT_TEMPERATURE = 22;
    private Map<OvenSituation, Integer> params;

    private OvenSituation situation = OvenSituation.HOLD;

    @Override
    public Product<T> toBake(T prepack) {
        return new Product<>(prepack).setFinished(Finished.RAW);
    }

    public Map<OvenSituation, Integer> getParams() {
        if (params.isEmpty())
            this.params = Map.of(situation, DEFAULT_TEMPERATURE);
        return this.params;
    }

    public void setParams(Integer temperature) {
        this.params = Map.of(situation, temperature);
    }

}
