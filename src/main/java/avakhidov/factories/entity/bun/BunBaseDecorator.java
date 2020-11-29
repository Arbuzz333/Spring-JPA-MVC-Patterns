package avakhidov.factories.entity.bun;



public class BunBaseDecorator implements BunDecorator {

    private final BunDecorator buckwheatBunDecorator;

    public BunBaseDecorator(BunDecorator buckwheatBunDecorator) {
        this.buckwheatBunDecorator = buckwheatBunDecorator;
    }

    @Override
    public Bun addIngredient(String characteristic) {
        return buckwheatBunDecorator.addIngredient(characteristic);
    }
}
