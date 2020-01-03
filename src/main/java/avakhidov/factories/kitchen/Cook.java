package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.MainIngredientEnum;

import java.util.List;
import java.util.Map;

public interface Cook {

    List<Product> createProductList(int count);

    List<Product> createProductList(Map<MainIngredientEnum, Integer> enumIntegerMap);

}
