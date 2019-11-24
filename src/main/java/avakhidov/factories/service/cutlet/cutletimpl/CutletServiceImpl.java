package avakhidov.factories.service.cutlet.cutletimpl;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.service.cutlet.CutletService;
import org.springframework.stereotype.Service;

@Service
public class CutletServiceImpl implements CutletService {

    @Override
    public <T extends Meat, S extends Meat> Cutlet getMoreFatCutlet(Cutlet<T> cutletFirst, Cutlet<S> cutletSecond) {
        if (cutletFirst.getMainIngredient().getFatMeat().compareTo(cutletSecond.getMainIngredient().getFatMeat()) <= 0) {
            return cutletFirst;
        }
        return cutletSecond;
    }
}
