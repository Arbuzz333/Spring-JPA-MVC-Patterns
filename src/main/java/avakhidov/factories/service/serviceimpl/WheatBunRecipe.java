package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.ingredient.SupplementIngredientDecorator;
import avakhidov.factories.entity.ingredient.SupplementPeanutDecorator;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WheatBunRecipe implements Recipe<Bun> {

    @Autowired
    private SupplementIngredientDecorator decorator;

    @Override
    public Bun cooked(double weight) {
        ParameterPrepareDough prepareDough = ParameterDoughEnum.WHEAT_FLOUR_FINE.toKneadTheDough();

        decorator.setIngredients(new SupplementPeanutDecorator());
        WheatBun wheatBun = new WheatBun(prepareDough, true, weight);
        wheatBun.supplement(decorator);
        wheatBun.setFinished(Finished.RAW);

        return wheatBun;
    }
}
