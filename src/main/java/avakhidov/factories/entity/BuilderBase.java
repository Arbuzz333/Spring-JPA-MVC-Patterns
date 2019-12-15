package avakhidov.factories.entity;


public class BuilderBase<P, R, T>  {

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