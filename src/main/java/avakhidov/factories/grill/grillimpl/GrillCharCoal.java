package avakhidov.factories.grill.grillimpl;

import avakhidov.factories.grill.Grill;
import avakhidov.factories.grill.energy.Energy;

public class GrillCharCoal implements Grill {

    @Override
    public <T> T barbecue(T roast) {
        return null;
    }

    @Override
    public Energy getEnergy() {
        return null;
    }
}
