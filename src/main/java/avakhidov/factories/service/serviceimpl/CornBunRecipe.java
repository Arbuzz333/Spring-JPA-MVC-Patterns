package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.CornFlour;
import avakhidov.factories.entity.CornBun;
import avakhidov.factories.entity.Bun;
import avakhidov.factories.service.Recipe;

import static avakhidov.factories.enums.GrindingFlour.COARSE;

public class CornBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked() {
        return new CornBun(new CornFlour(COARSE), true);
    }
}
