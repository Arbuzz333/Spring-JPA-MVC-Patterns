package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.BuckwheatFlour;
import avakhidov.factories.entity.BuckwheatBun;
import avakhidov.factories.entity.Bun;
import avakhidov.factories.service.Recipe;

import static avakhidov.factories.enums.GrindingFlour.FINE;

public class BuckwheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new BuckwheatBun(new BuckwheatFlour(FINE), true);
    }
}
