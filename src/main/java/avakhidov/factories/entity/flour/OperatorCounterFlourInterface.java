package avakhidov.factories.entity.flour;


public interface OperatorCounterFlourInterface {

    double coefficient_wheat = 0.67;

    double coefficient_corn = 0.55;

    double coefficient_buckwheat = 0.5;

    default double countFlour(double weight) {
        return coefficient_wheat * weight;
    }

}
