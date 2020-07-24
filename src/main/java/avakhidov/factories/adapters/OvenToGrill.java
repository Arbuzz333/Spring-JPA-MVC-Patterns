package avakhidov.factories.adapters;

import avakhidov.factories.entity.Product;
import avakhidov.factories.grill.Grill;
import avakhidov.factories.service.Oven;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



@Lazy
@Service
public class OvenToGrill<T extends Product> implements Oven<T> {

    private Grill grill;

    @Override
    public T toBake(T prepack) {
        return (T) grill.barbecue(prepack);
    }

    @Override
    public ParamsOven getParams() {
        switch (grill.getEnergy().getAggregation()) {
            case SOLID:
                return Grill.CoefficientCalorificToTemperature.grillCharCoalParams(grill.getEnergy().getEnergy());
            case GASEOUS:
                return Grill.CoefficientCalorificToTemperature.grillGasParams(grill.getEnergy().getEnergy());
            case LIQUID:
                return Grill.CoefficientCalorificToTemperature.grillDieselParams(grill.getEnergy().getEnergy());
        }
        return new ParamsOven(OvenSituation.INDEFINITE, 0, 0);
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }
}
