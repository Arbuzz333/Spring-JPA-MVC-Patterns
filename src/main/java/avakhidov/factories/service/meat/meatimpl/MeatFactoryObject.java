package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.service.meat.MeatCreator;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class MeatFactoryObject {

    private Map<MeatCode, MeatCreator> meatCreatorMap = new HashMap<>();

    public void registerMeatCreator(MeatCode code, MeatCreator creator) {
        meatCreatorMap.put(code, creator);
    }

    public <T extends Meat> T create(RecipeMeat recipe) {

        MeatCode meatCode = recipe.getMeatCode();
        MeatCreator meatCreator = meatCreatorMap.get(meatCode);
        if (meatCreator == null) {
            throw new UnsupportedOperationException("this code is unsupported: " + meatCode);
        }
        T meat = (T) meatCreator.creatMeat(recipe);

        return meat;
    }
}
