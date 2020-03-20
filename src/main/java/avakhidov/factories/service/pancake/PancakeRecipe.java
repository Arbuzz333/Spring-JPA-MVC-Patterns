package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.ingredient.Egg;
import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.order.OrderPancake;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PancakeRecipe implements PancakeVisitor {

    public List<OrderPancake<? extends Flour>> createListPancakeRecipe(PancakeRecipeCreate<? extends Flour>...creates) {
        List<OrderPancake<? extends Flour>> result = new ArrayList<>();
        for (PancakeRecipeCreate<? extends Flour> create : creates) {
            result.add(create.pancakeOrderCreate(this));
        }
        return result;
    }

    @Override
    public Pancake<BuckwheatFlour> pancakeBuckwheatVisit(PancakeBuckwheatRecipeCreate buckwheatOrderCreate) {
        PancakeBuckwheat pancakeBuckwheat = new PancakeBuckwheat(
                new PancakePrepareDough<>(
                        new BuckwheatFlour(GrindingFlour.MEDIUM),
                        36,
                        LocalTime.of(0, 40),
                        6.7,
                        new Egg("o category", 2)),
                0.055);
        pancakeBuckwheat.setFinished(Finished.RAW);
        return pancakeBuckwheat;
    }

    @Override
    public Pancake<CornFlour> pancakeCornVisit(PancakeCornRecipeCreate wheatOrderCreate) {
        PancakeCorn pancakeCorn = new PancakeCorn(
                new PancakePrepareDough<>(
                        new CornFlour(GrindingFlour.COARSE),
                        36,
                        LocalTime.of(1, 40),
                        8.5,
                        new Egg("1 category", 1)),
                0.065);
        pancakeCorn.setFinished(Finished.RAW);
        return pancakeCorn;
    }

    @Override
    public Pancake<WheatFlour> pancakeWheatVisit(PancakeWheatRecipeCreate cornOrderCreate) {
        PancakeWheat pancakeWheat = new PancakeWheat(
                new PancakePrepareDough<>(
                        new WheatFlour(GrindingFlour.FINE),
                        41,
                        LocalTime.of(0, 55),
                        9.1,
                        new Egg("2 category", 3)),
                0.045, new VealMeat(FatMeat.LOWFAT, new Calf()));
        pancakeWheat.setFinished(Finished.RAW);
        return pancakeWheat;
    }
}
