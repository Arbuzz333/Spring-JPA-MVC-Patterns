package avakhidov.factories.service;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.KindFlour;

import java.math.BigDecimal;


public interface Oven<T extends Product> {

    Product<T> toBake(T prepack);

    ParamsOven getParams();

    enum OvenSituation {
        HOLD,
        HOT,
        INDEFINITE
    }

    enum MinPartyBun {
        MIN_PARTY_WHEAT(KindFlour.WHEAT, BigDecimal.valueOf(55.0), 35),
        MIN_PARTY_CORN(KindFlour.CORN, BigDecimal.valueOf(45.0), 30),
        MIN_PARTY_BUCKWHEAT(KindFlour.BUCKWHEAT, BigDecimal.valueOf(35.0), 25);

        private KindFlour flour;
        private BigDecimal minPartyFlour;
        private Integer minPartyBun;

        private MinPartyBun(KindFlour flour, BigDecimal minPartyFlour, Integer minPartyBun) {
            this.flour = flour;
            this.minPartyFlour = minPartyFlour;
            this.minPartyBun = minPartyBun;
        }

        public KindFlour getFlour() {
            return flour;
        }

        public BigDecimal getMinParty() {
            return minPartyFlour;
        }

        public double getMinPartyBun() {
            return minPartyBun;
        }

        public static MinPartyBun getMinParty(KindFlour flour) {
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
