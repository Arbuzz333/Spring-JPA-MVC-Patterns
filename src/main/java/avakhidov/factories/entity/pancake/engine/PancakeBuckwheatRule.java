package avakhidov.factories.entity.pancake.engine;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Egg;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;

public class PancakeBuckwheatRule implements RuleGetPancake {

    @Value("#{new Double('${weight_buckwheat_pancake}')}")
    double buckwheatWeight;

    @Override
    public boolean evaluate(KindFlour expression) {
        return expression.equals(KindFlour.BUCKWHEAT);
    }

    @Override
    public Pancake<? extends Flour> getResult() {
        PancakeBuckwheat pancakeBuckwheat = new PancakeBuckwheat(
                new PancakePrepareDough<>(
                        new BuckwheatFlour(GrindingFlour.MEDIUM),
                        36,
                        LocalTime.of(0, 40),
                        6.7,
                        new Egg("o category", 2)),
                buckwheatWeight);
        pancakeBuckwheat.setFinished(Finished.RAW);
        return pancakeBuckwheat;
    }
}
