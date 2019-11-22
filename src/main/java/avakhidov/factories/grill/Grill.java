package avakhidov.factories.grill;

import avakhidov.factories.grill.energy.Energy;

public interface Grill {

    <T> T barbecue(T roast);

    Energy getEnergy();
}
