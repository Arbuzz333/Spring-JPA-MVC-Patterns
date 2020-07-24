package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.service.meat.MeatCreator;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.springframework.stereotype.Service;


@Service
public class PorkMeatCreator implements MeatCreator<PorkMeat> {

    @Override
    public PorkMeat creatMeat(RecipeMeat recipe) {
        return new PorkMeat(recipe.getMeatCode().getFatMeat(), (Pig) recipe.getLivestock());
    }

    @Override
    public MeatCode getMeatCode() {
        return MeatCode.PORK_SPECK;
    }
}
