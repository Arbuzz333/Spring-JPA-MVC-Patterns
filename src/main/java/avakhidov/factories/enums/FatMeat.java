package avakhidov.factories.enums;

public enum FatMeat {

    SPECK(50.0f, 80.0f),
    MEDIUMFAT(35.0f, 49.9f),
    LOWFAT(11.0f, 34.9f),
    DIETARY(0.0f, 10.9f);

    FatMeat(float min, float max) {
    }

    private float min;

    private float max;

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }
}
