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
        public ParameterPrepareDough toKneadTheDough() {
            return new ParameterPrepareDough<>(new BuckwheatFlour(GrindingFlour.MEDIUM), temperatureBuckwheatDough, timeBuckwheatDough);
        }
    },
    CORN_FLOUR_COARSE {
        public ParameterPrepareDough toKneadTheDough() {
            return new ParameterPrepareDough<>(new CornFlour(GrindingFlour.COARSE), temperatureCornDough, timeCornDough);
        }
    },
    WHEAT_FLOUR_FINE {
        public ParameterPrepareDough toKneadTheDough() {
            return new ParameterPrepareDough<>(new WheatFlour(GrindingFlour.FINE), temperatureWheatDough, timeWheat);
        }
    };

    private static int temperatureBuckwheatDough = 30;
    private static LocalTime timeBuckwheatDough = LocalTime.of(1, 0);

    private static int temperatureCornDough = 28;
    private static LocalTime timeCornDough = LocalTime.of(0, 40);

    private static int temperatureWheatDough = 32;
    private static LocalTime timeWheat = LocalTime.of(2, 30);

}
