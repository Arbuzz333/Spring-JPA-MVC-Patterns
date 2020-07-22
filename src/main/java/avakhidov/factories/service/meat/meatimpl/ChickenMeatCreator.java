package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.service.meat.MeatCreator;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.springframework.stereotype.Service;


@Service
public class ChickenMeatCreator implements MeatCreator<ChickenMeat> {

    @Override
    public ChickenMeat creatMeat(RecipeMeat recipe) {
        return new ChickenMeat(recipe.getMeatCode().getFatMeat(), (Chicken) recipe.getLivestock());
    }

    @Override
    public MeatCode getMeatCode() {
        return MeatCode.CHICKEN_DIETARY;
    }
}
