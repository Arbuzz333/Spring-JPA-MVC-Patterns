package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.OuterBuilderProduct;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class OuterBuilderChickenCutlet<P extends ChickenCutlet, R extends OuterBuilderChickenCutlet<? extends P, ?>>
        extends OuterBuilderProduct<P, R, ChickenMeat> {

    private boolean recipeReady = false;

    OuterBuilderChickenCutlet(P child) {
        super(child);
    }

    public R withMainIngredient(FatMeat fatMeat) {
        getNested().setMainIngredient(new ChickenMeat(fatMeat, new Chicken()));
        return self();
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
