package avakhidov.factories.service;

import avakhidov.factories.enums.KindFlour;


public interface Oven<T> {

    T toBake(T prepack);

    ParamsOven getParams();

    enum OvenSituation {
        HOLD,
        HOT,
        INDEFINITE
    }

    enum MinPartyBun {
        MIN_PARTY_WHEAT(KindFlour.WHEAT, 0.75, 35),
        MIN_PARTY_CORN(KindFlour.CORN, 0.80, 30),
        MIN_PARTY_BUCKWHEAT(KindFlour.BUCKWHEAT, 0.85, 25);

        private KindFlour flour;
        private double coefficientFlour;
        private Integer minPartyBun;

        private MinPartyBun(KindFlour flour, double coefficientFlour, Integer minPartyBun) {
            this.flour = flour;
            this.coefficientFlour = coefficientFlour;
            this.minPartyBun = minPartyBun;
        }

        public KindFlour getKindFlour() {
            return flour;
        }

        public double getCoefficientParty() {
            return minPartyBun / coefficientFlour;
        }

        public double getMinPartyBun() {
            return minPartyBun;
        }

        public static MinPartyBun getCoefficientParty(KindFlour flour) {
            MinPartyBun find = MinPartyBun.MIN_PARTY_WHEAT;
            MinPartyBun[] flourMinParties = MinPartyBun.values();
            for (MinPartyBun party : flourMinParties) {
                if (party.flour.equals(flour))
                    find = party;
                break;
            }
            return find;
        }

    }
    class ParamsOven {

        OvenSituation ovenSituation;

        Integer temperature;

        public ParamsOven(OvenSituation ovenSituation, Integer temperature) {
            this.ovenSituation = ovenSituation;
            this.temperature = temperature;
        }

        public OvenSituation getOvenSituation() {
            return ovenSituation;
        }

        public Integer getTemperature() {
            return temperature;
        }

    }
}
