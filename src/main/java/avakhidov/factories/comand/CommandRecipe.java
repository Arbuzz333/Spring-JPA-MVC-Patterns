package avakhidov.factories.comand;

import avakhidov.factories.entity.Product;
import avakhidov.factories.service.Recipe;

import java.util.UUID;

public abstract class CommandRecipe {

    Recipe recipe;
    double weight;

    private UUID uuid;

    public CommandRecipe(Recipe recipe, double weight) {
        this.recipe = recipe;
        this.weight = weight;
        this.uuid = UUID.randomUUID();
    }

    public abstract Product orderProduct();


    public UUID getUuid() {
        return uuid;
    }
}
