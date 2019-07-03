package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.Oven;

public class PreheatedOven<T> implements Oven<T> {

    @Override
    public Product<T> toBake(T prepack) {
        return new Product<T>().setPrepack(prepack).setFinished(Finished.FINISHED
        );
    }
}
