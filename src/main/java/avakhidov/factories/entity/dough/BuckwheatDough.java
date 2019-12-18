package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.enums.dough.KindDough;

public class BuckwheatDough extends Dough {

    private Manufacturer manufacturer;

    private BuckwheatDough(BuckwheatFlour flour, Manufacturer manufacturer, KindDough kindDough, double fat) {
        super(flour, kindDough, fat);
        this.manufacturer = manufacturer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public static BuckwheatDough buildBuckwheatDough(
            BuckwheatFlour buckwheatFlour, Manufacturer manufacturer, KindDough kindDoughBuild, double fatBuild) {
        BuckwheatDough dough = new BuckwheatDough.BuilderBuckwheatDough() {{
            withManufacturer(manufacturer);
            withFlour(buckwheatFlour);
            withKindDough(kindDoughBuild);
            withFat(fatBuild);
        }}.build();

        return dough;
    }

    private static class BuilderBuckwheatDough extends Dough.BuilderDough<BuckwheatFlour> {

        private Manufacturer manufacturer;

        void withManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }

        BuckwheatDough build() {
            return new BuckwheatDough(flour, manufacturer, kindDough, fat);
        }
    }

    public enum Manufacturer {
        HANDMADE,
        PREFABRICATED
    }
}
