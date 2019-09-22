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
        public ParameterPrepareDough toKneadTheDough(int temperature, LocalTime time) {
            return new ParameterPrepareDough<>(new BuckwheatFlour(GrindingFlour.MEDIUM), temperature, time);
        }
    },
    CORN_FLOUR_COARSE {
        public ParameterPrepareDough toKneadTheDough(int temperature, LocalTime time) {
            return new ParameterPrepareDough<>(new CornFlour(GrindingFlour.COARSE), temperature, time);
        }
    },
    WHEAT_FLOUR_FINE {
        public ParameterPrepareDough toKneadTheDough(int temperature, LocalTime time) {
            return new ParameterPrepareDough<>(new WheatFlour(GrindingFlour.FINE), temperature, time);
        }
    }

}
