package avakhidov.factories.enums;

public enum GrindingFlour {
    MEDIUM("medium grinding"),
    FINE("fine grinding"),
    COARSE("coarse grinding");

    private String title;

    GrindingFlour(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
