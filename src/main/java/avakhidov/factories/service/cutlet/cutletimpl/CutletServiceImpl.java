package avakhidov.factories.service.cutlet.cutletimpl;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.service.cutlet.CutletService;
import org.springframework.stereotype.Service;

@Service
public class CutletServiceImpl implements CutletService {

    @Override
    public Cutlet getMoreFatCutlet(Cutlet cutletFirst, Cutlet cutletSecond) {
        if (cutletFirst.getMeat().getFatMeat().compareTo(cutletSecond.getMeat().getFatMeat()) <= 0) {
            return cutletFirst;
        }
        return cutletSecond;
    }
}
