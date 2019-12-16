package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;

public class OuterBuilderProduct<P extends Product<T>, R extends OuterBuilderProduct<? extends P, ?, T>, T> extends BuilderBaseT<P, R, T> {

    protected OuterBuilderProduct(P child) {
        super(child);
    }

    public R withMainIngredient(T mainIngredient) {
        getNested().setMainIngredient(mainIngredient);
        return self();
    }

    public R withWeight(double weight) {
        getNested().setWeight(weight);
        return self();
    }

    public R withFinished(Finished finished) {
        getNested().setFinished(finished);
        return self();
    }

}
