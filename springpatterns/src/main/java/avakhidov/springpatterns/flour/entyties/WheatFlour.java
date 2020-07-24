package avakhidov.springpatterns.flour.entyties;


import avakhidov.springpatterns.flour.enums.GrindingFlour;
import avakhidov.springpatterns.flour.enums.KindFlour;




public class WheatFlour extends Flour {

    public WheatFlour(GrindingFlour grinding) {
        super(KindFlour.WHEAT, grinding);
    }

}
