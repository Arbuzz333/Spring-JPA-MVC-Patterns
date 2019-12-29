package avakhidov.factories.entity;


import avakhidov.factories.service.MainIngredient;

public class BuilderBase<P, R, T extends MainIngredient> {

    private P nested;
    private R returnBuilder;

    protected BuilderBase(P child) {
        nested = child;
    }

    protected P getNested() {
        return nested;
    }

    protected void injectReturnBuilder(R builder) {
        returnBuilder = builder;
    }

    protected R self() {
        return returnBuilder;
    }

    public P build() {
        return nested;
    }
}