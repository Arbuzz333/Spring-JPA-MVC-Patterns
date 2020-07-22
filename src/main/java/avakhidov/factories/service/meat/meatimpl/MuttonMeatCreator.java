package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.service.meat.MeatCreator;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.springframework.stereotype.Service;


@Service
public class MuttonMeatCreator implements MeatCreator<MuttonMeat> {

    @Override
    public MuttonMeat creatMeat(RecipeMeat recipe) {
        return new MuttonMeat(recipe.getMeatCode().getFatMeat(), (Sheep) recipe.getLivestock());
    }

    @Override
    public MeatCode getMeatCode() {
        return MeatCode.MUTTON_LOW_FAT;
    }
}
