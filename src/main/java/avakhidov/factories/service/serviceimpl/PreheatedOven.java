package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;


public class PreheatedOven<T extends Product<?>> implements Oven<T> {

    private static final int DEFAULT_TEMPERATURE = 180;

    private ParamsOven params;

    {
        params = new ParamsOven(OvenSituation.HOT, DEFAULT_TEMPERATURE);
    }

    @Override
    public T toBake(T prepack) {
        prepack.setFinished(Finished.FINISHED);
        return prepack;
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
