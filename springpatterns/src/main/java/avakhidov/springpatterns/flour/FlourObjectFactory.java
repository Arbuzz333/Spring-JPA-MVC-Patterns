package avakhidov.springpatterns.flour;


import avakhidov.springpatterns.flour.entyties.Flour;
import avakhidov.springpatterns.flour.enums.FlourCode;




public interface FlourObjectFactory {

    <T extends Flour> T getFlour(FlourCode code);
}
