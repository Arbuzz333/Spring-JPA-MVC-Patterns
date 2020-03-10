package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;


@Service
public class CornBunRecipe implements Recipe<Bun> {

    @Override
    public Bun cooked(double weight) {
        ParameterPrepareDough<?> parameterPrepareDough = ParameterDoughEnum.CORN_FLOUR_COARSE.toKneadTheDough();

        CornBun cornBun = new CornBun((ParameterPrepareDough<CornFlour>) parameterPrepareDough, true, weight);
        cornBun.setFinished(Finished.RAW);
        parameterPrepareDough.setKindDoughAndFat(KindDough.PUFF_PASTRY, 3.5);
        return cornBun;
    }
}
