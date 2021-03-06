package avakhidov.factories.enums.dough;

import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;


public enum ParameterDoughEnum implements BuildParameterPrepareDough {

    BUCKWHEAT_FLOUR_MEDIUM {
        public ParameterPrepareDough<BuckwheatFlour> toKneadTheDough() {
            return new ParameterPrepareDough<>(
                    new BuckwheatFlour(GrindingFlour.MEDIUM), KindDough.CHOUX_PASTRY,
                    temperatureBuckwheatDough, timeBuckwheatDough, fatBuckwheat);
        }
    },
    CORN_FLOUR_COARSE {
        public ParameterPrepareDough<CornFlour> toKneadTheDough() {
            return new ParameterPrepareDough<>(
                    new CornFlour(GrindingFlour.COARSE), KindDough.YEAST_DOUGH,
                    temperatureCornDough, timeCornDough, fatCorn);
        }
    },
    WHEAT_FLOUR_FINE {
        public ParameterPrepareDough<WheatFlour> toKneadTheDough() {
            return new ParameterPrepareDough<>(
                    new WheatFlour(GrindingFlour.FINE), KindDough.SHORTCRUST_PASTRY,
                    temperatureWheatDough, timeWheat, fatWheat);
        }
    };

    private static int temperatureBuckwheatDough = 30;
    private static LocalTime timeBuckwheatDough = LocalTime.of(1, 0);
    private static double fatBuckwheat = 7.5;

    private static int temperatureCornDough = 28;
    private static LocalTime timeCornDough = LocalTime.of(0, 40);
    private static double fatCorn = 5.2;

    private static int temperatureWheatDough = 32;
    private static LocalTime timeWheat = LocalTime.of(2, 30);
    private static double fatWheat = 4.5;

}
