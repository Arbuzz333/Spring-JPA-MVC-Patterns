package avakhidov.factories.comand;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;

public class CommandChickenCutlet extends CommandRecipe {

    public CommandChickenCutlet(ChickenCutletRecipe recipe, double weight) {
        super(recipe, weight);
    }

    @Override
    public ChickenCutlet orderProduct() {
        return (ChickenCutlet) recipe.cooked(weight);
    }
}
