package avakhidov.springpatterns.flour;

import avakhidov.anotations.MainRunner;
import avakhidov.springpatterns.flour.entyties.BuckwheatFlour;
import avakhidov.springpatterns.flour.entyties.CornFlour;
import avakhidov.springpatterns.flour.entyties.Flour;
import avakhidov.springpatterns.flour.entyties.WheatFlour;
import avakhidov.springpatterns.flour.enums.FlourCode;
import avakhidov.springpatterns.flour.enums.KindFlour;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class FlourObjectFactoryImpl implements FlourObjectFactory {

    private Map<FlourCode, Flour> flourMap = new HashMap<>();

    @Override
    public <T extends Flour> T getFlour(FlourCode code) {
        return (T) flourMap.get(code);
    }

    @MainRunner
    public void fillingFlourMap() {
        FlourCode[] values = FlourCode.values();
        for (FlourCode flourCode : values) {
            if (flourCode.getKindFlour().equals(KindFlour.CORN)) {
                flourMap.put(flourCode, new CornFlour(flourCode.getCoarse()));
            } else if (flourCode.getKindFlour().equals(KindFlour.BUCKWHEAT)) {
                flourMap.put(flourCode, new BuckwheatFlour(flourCode.getCoarse()));
            } else if (flourCode.getKindFlour().equals(KindFlour.WHEAT)) {
                flourMap.put(flourCode, new WheatFlour(flourCode.getCoarse()));
            }
        }
    }
}
