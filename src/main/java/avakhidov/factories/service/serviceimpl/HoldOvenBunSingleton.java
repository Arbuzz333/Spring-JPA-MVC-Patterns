package avakhidov.factories.service.serviceimpl;

import avakhidov.factories.entity.bun.Bun;

    public class HoldOvenBunSingleton {

    private static volatile HoldOven<Bun> instance;

    private static boolean flag = true;

    private HoldOvenBunSingleton() {
        if (flag) {
            flag = false;
        } else {
            throw new IllegalStateException("Already initialized");
        }
    }

    public static HoldOven<Bun> getInstance() {
        /* Todo set var levelUp to java 10 */
        HoldOven<Bun> result = instance;

        if (result == null) {
            synchronized (HoldOvenBunSingleton.class) {
                result = instance;
                if (result == null) {
                    instance = result = new HoldOven<>();
                }
            }
        }
        return result;
    }
}
