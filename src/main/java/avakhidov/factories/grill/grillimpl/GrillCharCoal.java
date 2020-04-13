package avakhidov.factories.grill.grillimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.grill.Grill;
import avakhidov.factories.grill.energy.Energy;
import avakhidov.factories.grill.energy.energyimpl.CharCoal;

public class GrillCharCoal implements Grill {

    private CharCoal energy;

    public GrillCharCoal(CharCoal enegy) {
        this.energy = enegy;
    }

    @Override
    public <T extends Product> Product barbecue(T roast) {
        return roast.setFinished(Finished.FINISHED);
    }

    @Override
    public Energy getEnergy() {
        return energy;
    }
}
