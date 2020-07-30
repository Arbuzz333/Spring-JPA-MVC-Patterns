package avakhidov.factories.cutlet;


import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.cutlet.cutletimpl.ChickenCutletRecipe;
import avakhidov.factories.service.cutlet.cutletimpl.PorkCutletRecipe;
import avakhidov.factories.service.cutlet.cutletimpl.VealCutletRecipe;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;





@Configuration
@ComponentScan("avakhidov.factories.service.meat.meatimpl")
public class CutletTestConfig {

    @Autowired
    private MeatServiceImpl service;

    @Bean
    public List<Recipe<Cutlet<? extends Meat>>> recipeList() {

        MeatServiceImpl porkMeatService = new MeatServiceImpl() {
            @Override
            public FatMeat getLessFatInMeat(FatMeat fatMeat) {
                return FatMeat.MEDIUM_FAT;
            }
            @Override
            public FatMeat getMoreFatInMeat(FatMeat fatMeat) {
                return FatMeat.SPECK;
            }
            @Override
            public PorkMeat buildMeat(Class<? extends Meat> clazz) {
                return new PorkMeat(FatMeat.SPECK, new Pig());
            }
        };

        MeatServiceImpl vealMeatService = new MeatServiceImpl() {
            @Override
            public FatMeat getLessFatInMeat(FatMeat fatMeat) {
                return FatMeat.LOW_FAT;
            }
            @Override
            public FatMeat getMoreFatInMeat(FatMeat fatMeat) {
                return FatMeat.MEDIUM_FAT;
            }
            @Override
            public VealMeat buildMeat(Class<? extends Meat> clazz) {
                return new VealMeat(FatMeat.MEDIUM_FAT, new Calf());
            }
        };

        Recipe porkCutletRecipe = new PorkCutletRecipe(porkMeatService);
        Recipe chickenCutletRecipe = new ChickenCutletRecipe(service);
        Recipe vealCutletRecipe = new VealCutletRecipe(vealMeatService);
        List<Recipe<Cutlet<? extends Meat>>> recipes = new ArrayList<>();
        recipes.add(porkCutletRecipe);
        recipes.add(chickenCutletRecipe);
        recipes.add(vealCutletRecipe);

        return recipes;
    }
}
