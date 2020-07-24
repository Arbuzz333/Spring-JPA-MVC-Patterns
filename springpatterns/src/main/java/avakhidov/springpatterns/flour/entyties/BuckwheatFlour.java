package avakhidov.springpatterns.flour.entyties;


import avakhidov.springpatterns.flour.enums.GrindingFlour;
import avakhidov.springpatterns.flour.enums.KindFlour;





public class BuckwheatFlour extends Flour {

    public BuckwheatFlour(GrindingFlour grinding) {
        super(KindFlour.BUCKWHEAT, grinding);
    }
}
