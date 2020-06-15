package avakhidov.factories.entity.pancake.engine;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.enums.KindFlour;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RilePancakeEngine {

    private static List<RuleGetPancake> rules = new ArrayList<>();

    static {
        rules.add(new PancakeBuckwheatRule());
        rules.add(new PancakeCornRule());
        rules.add(new PancakeWheatRule());
    }

    public Pancake<? extends Flour> process(KindFlour expression) {
        RuleGetPancake rule = rules
                .stream()
                .filter(r -> r.evaluate(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
        return rule.getResult();
    }
}
