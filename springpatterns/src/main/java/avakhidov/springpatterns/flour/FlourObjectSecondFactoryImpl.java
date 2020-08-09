package avakhidov.springpatterns.flour;

import avakhidov.springpatterns.flour.entyties.Flour;
import avakhidov.springpatterns.flour.enums.FlourCode;



public class FlourObjectSecondFactoryImpl implements FlourObjectFactory {

    @Override
    public <T extends Flour> T getFlour(FlourCode code) {
        return (T) new Flour(code.getKindFlour(), code.getGrindingFlour()){};
    }
}
