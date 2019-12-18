package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.dough.KindDough;

public class Dough<T extends Flour> {

    private T flour;

    private KindDough kindDough;

    private double fat;

    Dough(T flour, KindDough kindDough, double fat) {
        this.flour = flour;
        this.kindDough = kindDough;
        this.fat = fat;
    }

    public T getFlour() {
        return flour;
    }

    public KindDough getKindDough() {
        return kindDough;
    }

    public double getFat() {
        return fat;
    }

    public static class BuilderDough<T extends Flour> {

        T flour;
        KindDough kindDough;
        double fat;

        public void withFlour(T flour) {
            this.flour = flour;
        }

        public void withKindDough(KindDough kindDough) {
            this.kindDough = kindDough;
        }

        public void withFat(double fat) {
            this.fat = fat;
        }

        public Dough buildDough() {
            return new Dough(flour, kindDough, fat);
        }

    }
}
