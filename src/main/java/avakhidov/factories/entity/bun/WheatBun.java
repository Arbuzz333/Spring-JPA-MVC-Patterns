package avakhidov.factories.entity.bun;


import avakhidov.factories.service.BuildParameterPrepareDough;

public class WheatBun extends Bun {

    public WheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady) {
        super(parameterDough.toKneadTheDough(), recipeReady);
    }

    @Override
    protected void setKindDough() {
        //ToDo
    }
}
