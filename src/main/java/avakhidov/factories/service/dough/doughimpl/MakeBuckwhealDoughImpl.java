package avakhidov.factories.service.dough.doughimpl;

import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.dough.BuckwheatDough;
import avakhidov.factories.entity.dough.Dough;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.service.dough.MakeDough;

public class MakeBuckwhealDoughImpl implements MakeDough<BuckwheatFlour> {

    @Override
    public Dough baseMakerDough(ParameterPrepareDough<BuckwheatFlour> prepare, double fat) {
        return BuckwheatDough.buildBuckwheatDough(
                prepare.getFlour()
                , BuckwheatDough.Manufacturer.HANDMADE
                , prepare.getKindDough()
                , fat);
    }
}
