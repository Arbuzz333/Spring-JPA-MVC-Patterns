package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.Oven;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OvenWorks {

    private static final int INDEFINITE_DEFAULT_TEMPERATURE = 0;
    private static final Integer DEFAULT_MIN_PARTY_FLOUR = 0;

    private Oven<Bun> oven;
    private Bun bun;

    public Product<Bun> toBake() {
        return this.oven.toBake(bun);
    }

    public Oven.ParamsOven getParams() {
        if (oven == null)
            return new Oven.ParamsOven(Oven.OvenSituation.INDEFINITE, INDEFINITE_DEFAULT_TEMPERATURE);
        return oven.getParams();
    }

    public BigDecimal getMinPartyFlour() {
        if (this.bun == null)
            return BigDecimal.valueOf(DEFAULT_MIN_PARTY_FLOUR);
        Oven.MinPartyBun find = Oven.MinPartyBun.getMinParty(bun.getPrepack().getFlour().getKind());
        return find.getMinParty();
    }

    public double getMinPartyBun() {
        if (this.bun == null)
            return DEFAULT_MIN_PARTY_FLOUR;
        Oven.MinPartyBun find = Oven.MinPartyBun.getMinParty(bun.getPrepack().getFlour().getKind());
        return find.getMinPartyBun();
    }

    public OvenWorks setOven(Oven<Bun> oven) {
        this.oven = oven;
        return this;
    }

    public Oven<Bun> getOven() {
        return oven;
    }

    public OvenWorks setBun(Bun bun) {
        this.bun = bun;
        return this;
    }
}
