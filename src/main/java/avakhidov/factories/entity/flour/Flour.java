package avakhidov.factories.entity.flour;


import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;

// ToDo MainIngredient
public abstract class Flour extends Ingredient {

    private KindFlour kind;

    private GrindingFlour grinding;

    public Flour(KindFlour kind, GrindingFlour grinding) {
        this.kind = kind;
        this.grinding = grinding;
    }

    public KindFlour getKind() {
        return kind;
    }

    public GrindingFlour getGrinding() {
        return grinding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flour)) return false;
        Flour flour = (Flour) o;
        return getKind() == flour.getKind() &&
                getGrinding() == flour.getGrinding();
    }

}
