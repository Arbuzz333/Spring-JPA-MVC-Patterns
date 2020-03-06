package avakhidov.factories.entity.flour;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportOperatorOperatorCounterFlourService {

    @Value("#{new Double('${weight_bun}')}")
    double weight;

    @Value("#{new Double('${coefficient_buckwheat}')}")
    double coefficient_buckwheat;

    @Value("#{new Double('${coefficient_wheat}')}")
    double coefficient_wheat;

    @Value("#{new Double('${coefficient_corn}')}")
    double coefficient_corn;

    public Double getWeight() {
        return weight;
    }

    public double getCoefficientBuckwheat() {
        return coefficient_buckwheat;
    }

    public double getCoefficientCorn() {
        return coefficient_corn;
    }

    public double getCoefficientWheat() {
        return coefficient_wheat;
    }
}
