package avakhidov.factories.enums;

public enum  KindFlour {

    CORN("corn"),
    BUCKWHEAT("buckwheat"),
    WHEAT("wheat");

    private String title;

    KindFlour(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
