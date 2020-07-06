package avakhidov.factories.entity;

import avakhidov.factories.enums.Finished;

import java.io.Serializable;
import java.util.UUID;




public abstract class BaseProduct<T> implements Serializable {

    protected UUID uuid;

    protected boolean recipeReady = false;

    T mainIngredient;

    double weight;

    Finished finished;

    public abstract boolean isRecipeReady();

    public abstract double getWeight();

    public abstract T getMainIngredient();

    public abstract Finished getFinished();

    public abstract UUID getUuid();

}
