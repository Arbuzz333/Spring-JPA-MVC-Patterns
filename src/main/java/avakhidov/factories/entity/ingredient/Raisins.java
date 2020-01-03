package avakhidov.factories.entity.ingredient;

import java.util.Objects;

public class Raisins extends Ingredient {

    String raisins;

    public Raisins(String raisins) {
        this.raisins = raisins;
    }

    public Raisins() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Raisins)) return false;
        Raisins raisins = (Raisins) o;
        return Objects.equals(this.raisins, raisins.raisins);
    }

    @Override
    public String getName() {
        return raisins;
    }
}
