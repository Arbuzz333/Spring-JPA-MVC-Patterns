package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;

import java.util.List;

public interface Cook {

    List<Product> createProductList(int count);

}
