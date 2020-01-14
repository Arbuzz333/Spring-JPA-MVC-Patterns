package avakhidov.factories.entity.ingredient;

public class Ketchup extends Ingredient {

    private String kindKetchup;

    public Ketchup(String kindKetchup) {
        this.kindKetchup = kindKetchup;
    }

    @Override
    public String getName() {
        return kindKetchup;
    }
}
