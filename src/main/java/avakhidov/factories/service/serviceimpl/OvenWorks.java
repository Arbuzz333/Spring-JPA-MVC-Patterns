package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.Oven;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class OvenWorks implements Oven<Bun> {

    public static final Integer MIN_PARTY = 12;

    public static final BigDecimal MIN_FLOUR = BigDecimal.valueOf(12);

    private Oven<Bun> oven;

    @Override
    public Product<Bun> toBake(Bun prepack) {
        return this.oven.toBake(prepack);
    }

    @Override
    public Map<OvenSituation, Integer> getParams() {
        return oven.getParams();
    }

    public void setOven(Oven<Bun> oven) {
        this.oven = oven;
    }

    public Oven<Bun> getOven() {
        return oven;
    }
}
