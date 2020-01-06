package avakhidov.factories.comand;

import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;

import java.time.LocalTime;

public class CommandWheatBun extends CommandRecipe {

    private LocalTime localTime;

    public CommandWheatBun(WheatBunRecipe recipe, double weight, LocalTime localTime) {
        super(recipe, weight);
        this.localTime = localTime;
    }

    @Override
    public WheatBun orderProduct() {
        WheatBun wheatBun = (WheatBun) recipe.cooked(weight);
        wheatBun.setLocalTime(localTime);
        wheatBun.setKindDough();
        return wheatBun;
    }
}
