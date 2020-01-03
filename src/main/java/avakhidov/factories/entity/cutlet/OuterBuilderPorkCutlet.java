package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.OuterBuilderProduct;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class OuterBuilderPorkCutlet<P extends PorkCutlet, R extends OuterBuilderPorkCutlet<? extends P, ?>>
        extends OuterBuilderProduct<P, R, PorkMeat> {

    private boolean recipeReady = false;

    OuterBuilderPorkCutlet(P child) {
        super(child);
    }

    public R withRecipeReady(boolean recipeReady) {
        this.recipeReady = recipeReady;
        getNested().setRecipeReady(recipeReady);
        return self();
    }

    public R withSesameBun(BuildParameterPrepareDough prepareDough, Sesame sesame) {
            getNested().createSesameBun(prepareDough, recipeReady, sesame, getNested().getWeight() * 0.5);
            getNested().getSesameBun().setFinished(Finished.RAW);
        return self();
    }

    public R withKindDough(KindDough kindDough) {
        getNested().getSesameBun().getMainIngredient().setKindDough(kindDough);
        return self();
    }

}