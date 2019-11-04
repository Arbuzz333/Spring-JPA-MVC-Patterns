package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;


public class PreheatedOven<T extends Product> implements Oven<T> {

    private static final int DEFAULT_TEMPERATURE = 180;
    private static final Integer DEFAULT_MIN_PARTY = 24;

    private ParamsOven params;

    {
        params = new ParamsOven(OvenSituation.HOT, DEFAULT_TEMPERATURE);
    }

    @Override
    public Product<T> toBake(T prepack) {
        return new Product<>(prepack).setFinished(Finished.FINISHED
        );
    }

    @Override
    public ParamsOven getParams() {
        if (params.getTemperature() == null)
            this.params = new ParamsOven(OvenSituation.HOT, DEFAULT_TEMPERATURE);
        return this.params;
    }

    public void setParams(Integer temperature) {
        this.params = new ParamsOven(OvenSituation.HOT, temperature);
    }
}
