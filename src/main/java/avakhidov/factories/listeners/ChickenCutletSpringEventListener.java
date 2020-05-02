package avakhidov.factories.listeners;

import avakhidov.factories.entity.cutlet.ChickenCutlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChickenCutletSpringEventListener implements ApplicationListener<GenericSpringEvent<ChickenCutlet>> {

    private final Logger logger = LoggerFactory.getLogger(ChickenCutletSpringEventListener.class);

    @Override
    public void onApplicationEvent(GenericSpringEvent<ChickenCutlet> event) {
        if (event.getCutlet().getSesameBun() != null) {
            logger.info("The chicken cutlet has sesame bun " + event.getCutlet().toString());
        } else {
            logger.info("The chicken cutlet hasn't sesame bun " + event.getCutlet().toString());
        }

    }
}
