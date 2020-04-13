package avakhidov.factories.cache;

import avakhidov.factories.entity.Product;
import avakhidov.factories.service.MainIngredient;

public class ProductDouble {

    private Product<? extends MainIngredient> productOne;
    private Product<? extends MainIngredient> productTwo;

    public ProductDouble(Product<? extends MainIngredient> productOne, Product<? extends MainIngredient> productTwo) {
        this.productOne = productOne;
        this.productTwo = productTwo;
    }

    public Product<? extends MainIngredient> getProductOne() {
        return productOne;
    }

    public Product<? extends MainIngredient> getProductTwo() {
        return productTwo;
    }
}
