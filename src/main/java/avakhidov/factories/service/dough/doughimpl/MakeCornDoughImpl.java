package avakhidov.factories.service.dough.doughimpl;

import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.dough.CornDough;
import avakhidov.factories.entity.dough.Dough;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.service.dough.MakeDough;

public class MakeCornDoughImpl implements MakeDough<CornFlour> {

    @Override
    public Dough<CornFlour> baseMakerDough(ParameterPrepareDough<CornFlour> prepare, double fat) {
        return new CornDough(prepare.getFlour(), prepare.getKindDough(), fat);
    }
}
