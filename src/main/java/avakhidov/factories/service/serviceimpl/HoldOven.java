package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;

public class HoldOven<T extends Product<?>> implements Oven<T> {

    private static final int DEFAULT_TEMPERATURE = 22;

    private ParamsOven params;

    {
        params = new ParamsOven(OvenSituation.HOLD, DEFAULT_TEMPERATURE, MAX_OUTPUT);
    }

    @Override
    public T toBake(T prepack) {
        prepack.setFinished(Finished.RAW);
        return prepack;
    }

    @Override
    public ParamsOven getParams() {
        return this.params;
    }

    public void setParams(Integer temperature, int maxOutput) {
        this.params = new ParamsOven(OvenSituation.HOLD, temperature, maxOutput);
    }

}
