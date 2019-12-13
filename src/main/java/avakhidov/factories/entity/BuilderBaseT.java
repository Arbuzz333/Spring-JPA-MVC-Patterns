package avakhidov.factories.entity;


public class BuilderBaseT<P, R, T>  {

    private P nested;
    private R returnBuilder;

    protected BuilderBaseT(P child) {
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