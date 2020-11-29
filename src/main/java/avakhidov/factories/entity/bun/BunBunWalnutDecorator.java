package avakhidov.factories.entity.bun;

import avakhidov.factories.entity.ingredient.Walnut;


public class BunBunWalnutDecorator extends BunBaseDecorator {

    public BunBunWalnutDecorator(BunDecorator buckwheatBunDecorator) {
        super(buckwheatBunDecorator);
    }

    @Override
    public Bun addIngredient(String characteristic) {
        Bun bun = super.addIngredient(characteristic);
        bun.ingredientList.add(new Walnut(characteristic));
        return bun;
    }
}
