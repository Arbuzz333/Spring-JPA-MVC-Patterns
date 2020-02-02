package avakhidov.factories.entity.ingredient;

public class SourCream extends Ingredient {

    private String kindSourCream;

    public SourCream(String kindSourCream) {
        this.kindSourCream = kindSourCream;
    }

    @Override
    public String getName() {
        return kindSourCream;
    }
}
