package avakhidov.factories.service;

import avakhidov.factories.entity.Bun;

public interface RecipeBun {

    Bun makingBun(Recipe<Bun> recipe);
}
