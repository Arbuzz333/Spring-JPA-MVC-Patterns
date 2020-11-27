package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;

public final class PreheatedOvenBunSingleton {

    private static class SingletonHolder {
        private static final PreheatedOven<Bun> HOLDER_INSTANCE = new PreheatedOven<>();
    }

    public static PreheatedOven<Bun> getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
