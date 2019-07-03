package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.Bun;
import avakhidov.factories.service.Oven;
import org.springframework.stereotype.Service;

@Service
public class OvenWorks implements Oven<Bun> {

    private Oven<Bun> oven;

    @Override
    public Product<Bun> toBake(Bun prepack) {
        return this.oven.toBake(prepack);
    }

    public void setOven(Oven<Bun> oven) {
        this.oven = oven;
    }

    public Oven<Bun> getOven() {
        return oven;
    }
}
