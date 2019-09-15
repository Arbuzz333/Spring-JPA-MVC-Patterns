package avakhidov.factories.service.dough.doughimpl;

import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.dough.Dough;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.dough.WheatDough;
import avakhidov.factories.service.dough.MakeDough;

public class MakeWheatDoughImpl implements MakeDough<WheatFlour> {
    @Override
    public Dough baseMakerDough(ParameterPrepareDough<WheatFlour> prepare) {
        return new WheatDough();
    }
}
