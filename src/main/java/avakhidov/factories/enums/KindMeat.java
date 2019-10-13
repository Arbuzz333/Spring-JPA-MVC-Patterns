package avakhidov.factories.enums;

public enum KindMeat {

    PORK('p', 1),
    CHICKEN('c', 2),
    BEEF('b', 3),
    MUTTON('m', 4),
    VEAL('v', 5);

    private char code;

    private int index;

    KindMeat(char code, int index) {
        this.code = code;
        this.index = index;
    }

    public char getCode() {
        return code;
    }

    public int getIndex() {
        return index;
    }

}
