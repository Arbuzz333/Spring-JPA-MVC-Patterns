package avakhidov.factories.service.cutlet;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;

public interface CutletService {

    <T extends Meat, S extends Meat> Cutlet getMoreFatCutlet(Cutlet<T> cutletFirst, Cutlet<S> cutletSecond);
}
