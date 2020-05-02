package avakhidov.factories.comand;

import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.service.recipe.bun.CornBunRecipe;

public class CommandCornBun extends CommandRecipe {

    public CommandCornBun(CornBunRecipe recipe, double weight) {
        super(recipe, weight);
    }

    @Override
    public CornBun orderProduct() {
        return (CornBun) recipe.cooked(weight);
    }
}
