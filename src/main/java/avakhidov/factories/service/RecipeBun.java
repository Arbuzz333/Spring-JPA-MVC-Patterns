package avakhidov.factories.service;

import avakhidov.factories.entity.bun.Bun;

public interface RecipeBun {

    Bun makingBun(Recipe<Bun> recipe);
}
