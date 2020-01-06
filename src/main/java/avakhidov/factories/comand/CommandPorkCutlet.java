package avakhidov.factories.comand;

import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;

public class CommandPorkCutlet extends CommandRecipe {

    public CommandPorkCutlet(PorkCutletRecipe recipe, double weight) {
        super(recipe, weight);
    }

    @Override
    public PorkCutlet orderProduct() {
        return (PorkCutlet) recipe.cooked(weight);
    }
}
