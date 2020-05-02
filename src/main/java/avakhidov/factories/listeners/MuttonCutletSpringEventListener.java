package avakhidov.factories.listeners;

import avakhidov.factories.entity.cutlet.MuttonCutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.enums.dough.ParameterDoughEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MuttonCutletSpringEventListener implements ApplicationListener<GenericSpringEvent<MuttonCutlet>> {

    @Value("#{new Double('${coefficient_corn}')}")
    double coefficient_corn;

    private final Logger logger = LoggerFactory.getLogger(MuttonCutletSpringEventListener.class);

    @Override
    public void onApplicationEvent(GenericSpringEvent<MuttonCutlet> event) {
        MuttonCutlet muttonCutlet = event.getCutlet();
        if (muttonCutlet.getSesameBun() == null) {
            muttonCutlet.createSesameBun(
                    ParameterDoughEnum.CORN_FLOUR_COARSE, true, new Sesame(), coefficient_corn * muttonCutlet.getWeight());

            logger.info("For Mutton cutlet sesame bun was crated " + muttonCutlet.toString());
        }
    }
}
