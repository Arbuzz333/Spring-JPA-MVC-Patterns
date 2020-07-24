package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.service.meat.MeatCreator;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.springframework.stereotype.Service;


@Service
public class VealMeatCreator implements MeatCreator<VealMeat> {

    @Override
    public VealMeat creatMeat(RecipeMeat recipe) {
        return new VealMeat(recipe.getMeatCode().getFatMeat(), (Calf) recipe.getLivestock());
    }

    @Override
    public MeatCode getMeatCode() {
        return MeatCode.VEAL_MEDIUM_FAT;
    }
}
