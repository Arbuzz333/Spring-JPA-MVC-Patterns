package avakhidov.factories.service.orders;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.exception.BunNotVerificationException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderVerification {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderVerification.class);

    void verificationBun(Bun bun, KindFlour kindFlour, KindDough kindDough) {
        KindDough startKindDough = kindDough;
        boolean result;

        result = bun.getMainIngredient().getFlour().getKind().equals(kindFlour);

        if (bun.getMainIngredient().getFlour().getKind().equals(KindFlour.WHEAT)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            String ampm = formatter.format(LocalTime.now());

            if (ampm.contains("AM")) {
                kindDough = KindDough.PUFF_PASTRY;
            } else {
                kindDough = KindDough.YEAST_DOUGH;
            }
            logger.info("Verification Bun is replaced KindDough from {} to {}.", startKindDough.name(), kindDough.name());
        }
        result = bun.getMainIngredient().getKindDough().equals(kindDough) && result;
        if (!result) {
            throw new BunNotVerificationException(bun.getClass().getName(), bun.getUuid().toString(), kindDough.name() + kindFlour.name());
        }
        logger.info("Verification Bun is success, bun: {}.", bun.getClass().getName());
    }

    void verificationCutlet(Cutlet cutlet, KindMeat kindMeat, FatMeat fatMeat) {
        boolean result;

        Meat meat = (Meat) cutlet.getMainIngredient();

        result = meat.getKindMeat().equals(kindMeat);
        result = meat.getFatMeat().equals(fatMeat) && result;

        if (!result) {
            throw new BunNotVerificationException(cutlet.getClass().getName(), cutlet.getUuid().toString(), fatMeat.name() + kindMeat.name());
        }
        logger.info("Verification Cutlet is success, Cutlet: {}.", cutlet.getClass().getName());
    }
}
