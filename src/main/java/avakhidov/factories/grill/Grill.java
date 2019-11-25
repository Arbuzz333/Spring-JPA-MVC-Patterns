package avakhidov.factories.grill;

import avakhidov.factories.entity.Product;
import avakhidov.factories.grill.energy.Energy;
import avakhidov.factories.service.Oven;

public interface Grill {

    <T extends Product> Product barbecue(T roast);

    Energy getEnergy();

    enum CoefficientCalorificToTemperature {
        ;
        public static Oven.ParamsOven grillGasParams(Energy.Calorific calorific) {
            final int maxOutput = 25;
            Oven.ParamsOven paramsOven =
                    new Oven.ParamsOven(Oven.OvenSituation.HOT, (int) (calorific.getCalorificValue() * 1.55), maxOutput);

            return paramsOven;
        }
        public static Oven.ParamsOven grillDieselParams(Energy.Calorific calorific) {
            final int maxOutput = 21;
            Oven.ParamsOven paramsOven =
                    new Oven.ParamsOven(Oven.OvenSituation.HOT, (int) (calorific.getCalorificValue() * 1.15), maxOutput);

            return paramsOven;
        }
        public static Oven.ParamsOven grillCharCoalParams(Energy.Calorific calorific) {
            final int maxOutput = 18;
            Oven.ParamsOven paramsOven =
                    new Oven.ParamsOven(Oven.OvenSituation.HOT, (int) (calorific.getCalorificValue() * 0.95), maxOutput);

            return paramsOven;
        }
    }
}
