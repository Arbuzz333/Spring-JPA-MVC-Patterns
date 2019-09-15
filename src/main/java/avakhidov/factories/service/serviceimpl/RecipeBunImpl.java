package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.RecipeBun;
import org.springframework.stereotype.Component;

@Component
public class RecipeBunImpl implements RecipeBun {

    @Override
    public Bun makingBun(Recipe<Bun> recipe) {
        return recipe.cooked();
    }
}
