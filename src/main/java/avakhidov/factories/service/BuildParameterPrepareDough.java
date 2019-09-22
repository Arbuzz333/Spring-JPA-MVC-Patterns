package avakhidov.factories.service;


import avakhidov.factories.entity.dough.ParameterPrepareDough;

import java.time.LocalTime;


public interface BuildParameterPrepareDough {

    ParameterPrepareDough toKneadTheDough(int temperature, LocalTime time);
}
