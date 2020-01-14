package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;

public interface PancakeVisitor {

    <T extends Flour> Pancake<T> pancakeBuckwheatVisit(PancakeBuckwheatRecipeCreate buckwheatOrderCreate);

    <T extends Flour> Pancake<T> pancakeWheatVisit(PancakeWheatRecipeCreate wheatOrderCreate);

    <T extends Flour> Pancake<T> pancakeCornVisit(PancakeCornRecipeCreate cornOrderCreate);
}
