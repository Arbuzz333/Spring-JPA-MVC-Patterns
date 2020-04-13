package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;

public interface PancakeVisitor {

    <T extends Flour> Pancake<T> pancakeBuckwheatVisit(PancakeBuckwheatRecipeCreate buckwheatOrderCreate);

    <S extends Flour> Pancake<S> pancakeWheatVisit(PancakeWheatRecipeCreate wheatOrderCreate);

    <E extends Flour> Pancake<E> pancakeCornVisit(PancakeCornRecipeCreate cornOrderCreate);
}
