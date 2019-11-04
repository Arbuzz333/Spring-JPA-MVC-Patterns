package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;

public class HoldOven<T extends Product> implements Oven<T> {

    private static final int DEFAULT_TEMPERATURE = 22;

    private ParamsOven params;

    {
        params = new ParamsOven(OvenSituation.HOLD, DEFAULT_TEMPERATURE);
    }

    @Override
    public Product<T> toBake(T prepack) {
        return new Product<>(prepack).setFinished(Finished.RAW);
    }

    @Override
    public ParamsOven getParams() {
        if (params == null || params.getTemperature() == null)
            this.params = new ParamsOven(OvenSituation.HOLD, DEFAULT_TEMPERATURE);
        return this.params;
    }

    public void setParams(Integer temperature) {
        this.params = new ParamsOven(OvenSituation.HOLD, temperature);
    }

}
