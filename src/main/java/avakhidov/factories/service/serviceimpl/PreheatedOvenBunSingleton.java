package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;

public final class PreheatedOvenBunSingleton {

    private static PreheatedOven<Bun> instance;

    private PreheatedOvenBunSingleton() {
        if (instance == null) {
            instance = new PreheatedOven<>();
        } else {
            throw new IllegalStateException("PreheatedOvenBunSingleton already initialized.");
        }
    }

    public static synchronized PreheatedOven<Bun> getInstance() {
        if (instance == null) {
            instance = new PreheatedOven<>();
        }
        return instance;
    }
}
