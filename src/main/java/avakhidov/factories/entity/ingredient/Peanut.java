package avakhidov.factories.entity.ingredient;

import java.util.Objects;


public class Peanut extends Ingredient {

    private String peanut;

    public Peanut(String peanut) {
        this.peanut = peanut;
    }

    Peanut() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peanut)) return false;
        Peanut p = (Peanut) o;
        return Objects.equals(peanut, p.peanut);
    }

    @Override
    public String getName() {
        return peanut;
    }
}
