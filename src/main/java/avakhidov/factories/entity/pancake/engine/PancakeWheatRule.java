package avakhidov.factories.entity.pancake.engine;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.ingredient.Egg;
import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;

public class PancakeWheatRule implements RuleGetPancake {

    @Value("#{new Double('${weight_wheat_pancake}')}")
    double wheatWeight;

    @Override
    public boolean evaluate(KindFlour expression) {
        return expression.equals(KindFlour.WHEAT);
    }

    @Override
    public Pancake<? extends Flour> getResult() {
        PancakeWheat pancakeWheat = new PancakeWheat(
                new PancakePrepareDough<>(
                        new WheatFlour(GrindingFlour.FINE),
                        41,
                        LocalTime.of(0, 55),
                        9.1,
                        new Egg("2 category", 3)),
                wheatWeight, new VealMeat(FatMeat.LOWFAT, new Calf()));
        pancakeWheat.setFinished(Finished.RAW);
        return pancakeWheat;
    }
}
