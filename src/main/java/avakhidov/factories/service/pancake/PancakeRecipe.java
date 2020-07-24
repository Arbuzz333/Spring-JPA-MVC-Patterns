package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.entity.pancake.engine.RilePancakeEngine;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.order.OrderPancake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PancakeRecipe implements PancakeVisitor {

    @Autowired
    private RilePancakeEngine engine;

    public List<OrderPancake<? extends Flour>> createListPancakeRecipe(List<PancakeRecipeCreate<? extends Flour>> creates) {
        List<OrderPancake<? extends Flour>> result = new ArrayList<>();
        for (PancakeRecipeCreate<? extends Flour> create : creates) {
            result.add(create.pancakeOrderCreate(this));
        }
        return result;
    }

    @Override
    public Pancake<BuckwheatFlour> pancakeBuckwheatVisit(PancakeBuckwheatRecipeCreate buckwheatOrderCreate) {
        Pancake<? extends Flour> process = engine.process(KindFlour.BUCKWHEAT);
        return (PancakeBuckwheat) process;
    }

    @Override
    public Pancake<CornFlour> pancakeCornVisit(PancakeCornRecipeCreate wheatOrderCreate) {
        Pancake<? extends Flour> process = engine.process(KindFlour.CORN);
        return (PancakeCorn) process;
    }

    @Override
    public Pancake<WheatFlour> pancakeWheatVisit(PancakeWheatRecipeCreate cornOrderCreate) {
        Pancake<? extends Flour> process = engine.process(KindFlour.WHEAT);
        return (PancakeWheat) process;
    }
}
