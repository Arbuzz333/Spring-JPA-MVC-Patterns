package avakhidov.config;

import avakhidov.anotations.TimesOfDayModification;
import avakhidov.enumes.TimesOfDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;


import static org.springframework.boot.context.config.ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY;

public class TimesOfDayConditional implements Condition {

    @Value("#{new Double('${weight_bun}')}")
    double weightOne;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> timesOfDayModification = metadata.getAllAnnotationAttributes(TimesOfDayModification.class.getName());
        List<Object> modList = timesOfDayModification.get("mod");
        TimesOfDay mod = (TimesOfDay) modList.get(0);

        String[] modProperties = context.getEnvironment().getActiveProfiles();
        String modProperties22 = context.getEnvironment().getProperty(ACTIVE_PROFILES_PROPERTY);

        String profile = Arrays.stream(modProperties).filter(m -> m.equals(TimesOfDay.NIGHT.name())).findFirst().orElse(TimesOfDay.DEFAULT.name());
        return mod.name().equalsIgnoreCase(profile);
    }
}
