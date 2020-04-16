package avakhidov.factories.cache;

public enum CacheNamesEnum {

    PRODUCT_EHCACHE("productEhcache"),
    CUTLET_EHCACHE("cutletEhcache");

    private String code;

    CacheNamesEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
