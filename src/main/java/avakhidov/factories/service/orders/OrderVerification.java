package avakhidov.factories.service.orders;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.exception.BunNotVerificationException;

public class OrderVerification {

    public void verificationBun(Bun bun, KindFlour kindFlour, KindDough kindDough) {
        boolean result;

        result = bun.getMainIngredient().getFlour().getKind().equals(kindFlour);
        result = bun.getMainIngredient().getKindDough().equals(kindDough) && result;

        if (!result) {
            throw new BunNotVerificationException(bun.getClass().getName(), bun.getUuid().toString(), kindDough.name() + kindFlour.name());
        }

    }
}
