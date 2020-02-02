package avakhidov.factories.entity.ingredient;

import java.util.Objects;

public class Walnut extends Ingredient {

    private String walnut;

    public Walnut(String walnut) {
        this.walnut = walnut;
    }

    public Walnut() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Walnut)) return false;
        Walnut walnut1 = (Walnut) o;
        return Objects.equals(walnut, walnut1.walnut);
    }

    @Override
    public String getName() {
        return walnut;
    }
}
