package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;


@Service
public class BuckwheatBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(double weight) {
        BuckwheatBun bun = BuckwheatBun.builderBuckwheat()
                .withMainIngredient(ParameterDoughEnum.BUCKWHEAT_FLOUR_MEDIUM.toKneadTheDough())
                .withFinished(Finished.RAW)
                .withRecipeReady(true)
                .withWeight(weight)
                .build();

        return bun;
    }
}
