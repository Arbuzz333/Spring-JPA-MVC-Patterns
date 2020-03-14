package avakhidov.factories.service.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.dough.Dough;
import avakhidov.factories.entity.dough.ParameterPrepareDough;

public interface MakeDough<T extends Flour> {

    Dough<T> baseMakerDough(ParameterPrepareDough<T> prepare, double fat);
}
