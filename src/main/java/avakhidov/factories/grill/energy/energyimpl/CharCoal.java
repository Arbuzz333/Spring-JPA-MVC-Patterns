package avakhidov.factories.grill.energy.energyimpl;

import avakhidov.factories.grill.energy.Energy;

public class CharCoal implements Energy {

    private Calorific calorific;

    public CharCoal(Calorific calorific) {
        this.calorific = calorific;
    }

    @Override
    public Calorific getEnergy() {
        return this.calorific;
    }

    @Override
    public StateOfAggregation getAggregation() {
        return StateOfAggregation.SOLID;
    }
}
