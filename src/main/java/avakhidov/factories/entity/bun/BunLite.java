package avakhidov.factories.entity.bun;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Ingredient;

import java.util.Date;
import java.util.List;

public class BunLite {

    private Date date;

    private List<Ingredient> additionalIngredient;

    private Product<ParameterPrepareDough<? extends Flour>> product;

    public BunLite(
            Date date,
            List<Ingredient> additionalIngredient,
            Product<ParameterPrepareDough<? extends Flour>> product) {
        this.date = date;
        this.additionalIngredient = additionalIngredient;
        this.product = product;
    }

    public List<Ingredient> getAdditionalIngredient() {
        return additionalIngredient;
    }

    public Product<ParameterPrepareDough<? extends Flour>> getProduct() {
        return product;
    }

    public Date getDate() {
        return date;
    }
}
