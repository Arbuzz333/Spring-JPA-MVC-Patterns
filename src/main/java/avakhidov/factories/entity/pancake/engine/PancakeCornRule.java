package avakhidov.factories.entity.pancake.engine;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Egg;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;

public class PancakeCornRule implements RuleGetPancake {

    @Value("#{new Double('${weight_corn_pancake}')}")
    double cornWeight;

    @Override
    public boolean evaluate(KindFlour expression) {
        return expression.equals(KindFlour.CORN);
    }

    @Override
    public Pancake<? extends Flour> getResult() {
        PancakeCorn pancakeCorn = new PancakeCorn(
                new PancakePrepareDough<>(
                        new CornFlour(GrindingFlour.COARSE),
                        36,
                        LocalTime.of(1, 40),
                        8.5,
                        new Egg("1 category", 1)),
                cornWeight);
        pancakeCorn.setFinished(Finished.RAW);
        return pancakeCorn;
    }
}
