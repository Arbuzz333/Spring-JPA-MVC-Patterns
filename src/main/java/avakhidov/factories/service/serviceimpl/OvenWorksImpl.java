package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.OvenWorks;

import java.math.BigDecimal;


    public class OvenWorksImpl<T extends Product<ParameterPrepareDough>> implements OvenWorks<T> {

    private static final int INDEFINITE_DEFAULT_TEMPERATURE = 0;
    private static final Integer DEFAULT_MIN_PARTY_FLOUR = 0;

    private Oven<T> oven;
    private T product;

    public OvenWorksImpl(Oven<T> oven, T product) {
        this.oven = oven;
        this.product = product;
    }

    @Override
    public T toBake() {
        return this.oven.toBake(product);
    }

    @Override
    public Oven.ParamsOven getParams() {
        if (oven == null)
            return new Oven.ParamsOven(Oven.OvenSituation.INDEFINITE, INDEFINITE_DEFAULT_TEMPERATURE, Oven.MAX_OUTPUT);
        return oven.getParams();
    }

    @Override
    public BigDecimal getMinPartyIngredient(int quantity) {
        if (this.product == null)
            return BigDecimal.valueOf(DEFAULT_MIN_PARTY_FLOUR);
        Oven.MinPartyBun find = Oven.MinPartyBun.getCoefficientParty(product.getPrepack().getFlour().getKind());
        return BigDecimal.valueOf(find.getCoefficientParty() * product.getWeight() * quantity);
    }

    @Override
    public double getMinPartyProduct() {
        if (this.product == null)
            return DEFAULT_MIN_PARTY_FLOUR;
        Oven.MinPartyBun find = Oven.MinPartyBun.getCoefficientParty(product.getPrepack().getFlour().getKind());
        return find.getMinPartyBun();
    }

    @Override
    public OvenWorksImpl setOven(Oven<T> oven) {
        this.oven = oven;
        return this;
    }

    public Oven<T> getOven() {
        return oven;
    }

    public OvenWorksImpl setProduct(T bun) {
        this.product = bun;
        return this;
    }
}
