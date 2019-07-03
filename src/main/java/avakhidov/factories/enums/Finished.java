package avakhidov.factories.enums;

public enum Finished {

    FINISHED(1),
    RAW(0);

    private int title;

    Finished(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }

}
