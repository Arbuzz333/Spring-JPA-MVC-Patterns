package avakhidov.factories.cache;

public enum CacheParameters {

    FIRST_CUTLET("FirstCutlet"),

    FOUR_CUTLET("FourCutlet"),

    FIVE_CUTLET("FiveCutlet"),

    SIX_CUTLET("SixCutlet"),

    SEVEN_CUTLET("SevenCutlet"),

    EIGHT_CUTLET("EightCutlet");

    CacheParameters(String firstCutlet) {
        this.name = name();
    }

    private String name;

    public String getName() {
        return this.name;
    }
}
