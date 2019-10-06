package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.RecipeBun;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class RecipeBunImpl implements RecipeBun {

    @Override
    public Bun makingBun(Recipe<Bun> recipe) {
        //Todo
        return recipe.cooked(0,  LocalTime.of(0, 0));
    }
}
