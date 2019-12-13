package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.BuilderProduct2;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class BuilderPorkCutlet2<P extends PorkCutlet, R extends BuilderPorkCutlet2<? extends P, ?>>
        extends BuilderProduct2<P, R, PorkMeat> {

    boolean recipeReady = false;

    BuilderPorkCutlet2(P child) {
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

}