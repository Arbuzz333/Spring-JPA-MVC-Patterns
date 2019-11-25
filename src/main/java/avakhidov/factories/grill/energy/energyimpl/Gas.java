package avakhidov.factories.grill.energy.energyimpl;

import avakhidov.factories.grill.energy.Energy;

public class Gas implements Energy {

    private Calorific calorific;

    public Gas(Calorific calorific) {
        this.calorific = calorific;
    }

    @Override
    public Calorific getEnergy() {
        return this.calorific;
    }

    @Override
    public StateOfAggregation getAggregation() {
        return StateOfAggregation.GASEOUS;
    }
}
