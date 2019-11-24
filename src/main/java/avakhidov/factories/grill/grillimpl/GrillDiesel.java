package avakhidov.factories.grill.grillimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.grill.Grill;
import avakhidov.factories.grill.energy.Energy;
import avakhidov.factories.grill.energy.energyimpl.DieselFuel;
import avakhidov.factories.grill.energy.energyimpl.Gas;

public class GrillDiesel implements Grill {

    private DieselFuel energy;

    public GrillDiesel(DieselFuel energy) {
        this.energy = energy;
    }

    public <T extends Product> Product barbecue(T roast) {
        return roast.setFinished(Finished.FINISHED);
    }

    @Override
    public Energy getEnergy() {
        return energy;
    }
}
