package avakhidov.factories.cache.services;

import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.MuttonCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.cutlet.VealCutlet;
import avakhidov.factories.entity.flour.ReportOperatorCounterFlourService;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.listeners.MuttonCutletSpringEventListener;
import avakhidov.factories.service.meat.meatimpl.MeatServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

import static avakhidov.factories.enums.dough.ParameterDoughEnum.WHEAT_FLOUR_FINE;

public enum CreateCutletByBuildersEnum implements CreateCutletByBuilders {

    CREATE_MUTTON_CUTLET(1) {
        @Override
        public Cutlet<? extends Meat> createCutletByIndex() {
            MuttonCutlet result;

            result = MuttonCutlet.builderMuttonCutlet()
                    .withMainIngredient(FatMeat.SPECK)
                    .withRecipeReady(true)
                    .withFinished(Finished.RAW)
                    .withWeight(weightMuttonCutlet)
                    .build();
            eventListener.onApplicationEvent(result.getListener());

            return result;
        }
    },
    CREATE_PORK_CUTLET(2){
        @Override
        public Cutlet<? extends Meat> createCutletByIndex() {
            PorkCutlet result = PorkCutlet.builderPorkCutlet()
                    .withMainIngredient(FatMeat.MEDIUMFAT)
                    .withRecipeReady(true)
                    .withFinished(Finished.RAW)
                    .withWeight(weightPorkCutlet)
                    .withSeasamBun(coefficient_buckwheat * weightPorkCutlet)
                    .build();
            return result;
        }
    },
    CREATE_CHICKEN_CUTLET(3),

    CREATE_VEAL_CUTLET(4) {
        @Override
        public Cutlet<? extends Meat> createCutletByIndex() {
            VealCutlet result = VealCutlet.builderVealCutlet()
                    .withMainIngredient((VealMeat) meatService.buildMeat(VealMeat.class))
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withWeight(weightChickenCutlet)
                    .build();
            Cutlet<? extends Meat>.SesameBun SesameBun = result.builderSesameBun()
                    .withMainIngredient(WHEAT_FLOUR_FINE.toKneadTheDough())
                    .withFinished(Finished.RAW)
                    .withRecipeReady(true)
                    .withKindDough(KindDough.SHORTCRUST_PASTRY)
                    .withWeight(weightChickenCutlet * coefficient_wheat)
                    .build();
            return result;
        }
    };

    public static double getWeightChickenCutlet() {
        return weightChickenCutlet;
    }

    private static double weightMuttonCutlet;
    private static double weightChickenCutlet;
    private static double weightPorkCutlet;
    private static double coefficient_buckwheat;
    private static double coefficient_wheat;
    private static MuttonCutletSpringEventListener eventListener;
    private static MeatServiceImpl meatService;

    private long id;

    CreateCutletByBuildersEnum(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static CreateCutletByBuildersEnum getCreateCutletByBuildersEnumById(long id) {
        for (CreateCutletByBuildersEnum cutletByBuildersEnum : CreateCutletByBuildersEnum.values()) {
            if (cutletByBuildersEnum.getId() == id) {
                return cutletByBuildersEnum;
            }
        }
        return CREATE_CHICKEN_CUTLET;
    }

    @Component
    static class ReportOperatorCounterFlour {

        private final ReportOperatorCounterFlourService report;

        private ReportOperatorCounterFlour(ReportOperatorCounterFlourService report) {
            this.report = report;
        }

        @PostConstruct
        private void postConstruct() {
            for (CreateCutletByBuildersEnum flour : EnumSet.allOf(CreateCutletByBuildersEnum.class)) {
                flour.setDataPrepareService(report);
            }
        }
    }

    private void setDataPrepareService(ReportOperatorCounterFlourService report) {
        weightMuttonCutlet = report.getWeightMuttonCutlet();
        weightChickenCutlet = report.getWeightChickenCutlet();
        weightPorkCutlet = report.getWeightPorkCutlet();
        coefficient_buckwheat = report.getCoefficientBuckwheat();
        coefficient_wheat = report.getCoefficientWheat();
        eventListener = report.getEventListener();
        meatService = report.getMeatService();
    }
}
