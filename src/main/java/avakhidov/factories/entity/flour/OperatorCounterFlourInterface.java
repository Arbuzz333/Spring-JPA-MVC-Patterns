package avakhidov.factories.entity.flour;


import static avakhidov.factories.entity.flour.FlourCounterServiceAspect.OperatorOperatorCounterFlour.coefficient_wheat;
import static avakhidov.factories.entity.flour.FlourCounterServiceAspect.OperatorOperatorCounterFlour.weight;

public interface OperatorCounterFlourInterface {

    default double countFlour() {
        return coefficient_wheat * weight;
    }

}
