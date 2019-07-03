package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Bun;
import avakhidov.factories.entity.WheatFlour;
import avakhidov.factories.entity.WheatBun;
import avakhidov.factories.service.Recipe;

import static avakhidov.factories.enums.GrindingFlour.MEDIUM;

public class WheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new WheatBun(new WheatFlour(MEDIUM), true);
    }
}
