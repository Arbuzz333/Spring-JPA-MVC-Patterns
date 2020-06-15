package avakhidov.factories.entity.pancake.engine;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.enums.KindFlour;

public interface RuleGetPancake {

    boolean evaluate(KindFlour expression);

    Pancake<? extends Flour> getResult();
}
