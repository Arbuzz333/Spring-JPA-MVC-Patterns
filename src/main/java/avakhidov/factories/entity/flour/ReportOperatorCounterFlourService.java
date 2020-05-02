package avakhidov.factories.entity.flour;

import avakhidov.factories.listeners.MuttonCutletSpringEventListener;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportOperatorCounterFlourService {

    @Value("#{new Double('${weight_bun}')}")
    double weight;

    @Value("#{new Double('${coefficient_buckwheat}')}")
    double coefficient_buckwheat;

    @Value("#{new Double('${coefficient_wheat}')}")
    double coefficient_wheat;

    @Value("#{new Double('${coefficient_corn}')}")
    double coefficient_corn;

    @Value("#{new Double('${weight_mutton_cutlet}')}")
    private double weightMuttonCutlet;

    @Value("#{new Double('${weight_pork_cutlet}')}")
    private double weightPorkCutlet;

    @Value("#{new Double('${weight_chicken_cutlet}')}")
    private double weightChickenCutlet;

    private final MuttonCutletSpringEventListener eventListener;

    private final MeatServiceImpl meatService;

    public ReportOperatorCounterFlourService(
            MuttonCutletSpringEventListener eventListener,
            MeatServiceImpl meatService) {
        this.eventListener = eventListener;
        this.meatService = meatService;
    }

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

    public MuttonCutletSpringEventListener getEventListener() {
        return eventListener;
    }

    public double getWeightMuttonCutlet() {
        return weightMuttonCutlet;
    }

    public double getWeightPorkCutlet() {
        return weightPorkCutlet;
    }

    public double getWeightChickenCutlet() {
        return weightChickenCutlet;
    }

    public MeatServiceImpl getMeatService() {
        return meatService;
    }
}
