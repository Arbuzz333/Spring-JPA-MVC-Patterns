package avakhidov.factories.entity.bun;

import avakhidov.factories.entity.ingredient.Peanut;


public class BunBunPeanutDecorator extends BunBaseDecorator {

    public BunBunPeanutDecorator(BunDecorator buckwheatBunDecorator) {
        super(buckwheatBunDecorator);
    }

    @Override
    public Bun addIngredient(String characteristic) {
        Bun bun = super.addIngredient(characteristic);
        bun.ingredientList.add(new Peanut(characteristic));
        return bun;
    }
}
