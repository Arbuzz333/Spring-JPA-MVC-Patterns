package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenBun;
import avakhidov.factories.annotations.KitchenCutlet;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.utility.MainUtility;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrdersMaker {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(OrdersMaker.class);

    private final Map<String, MethodHandleBun> CLASS_HANDLES_BUN = new TreeMap<>();
    private final Map<String, MethodHandleCutlet> CLASS_HANDLES_CUTLET = new TreeMap<>();

    private final CommandOrders commandOrders;
    private final OrdersSplitter ordersSplitter;
    private final OrderVerification verification;

    public OrdersMaker(CommandOrders commandOrders, OrdersSplitter ordersSplitter, OrderVerification verification) {
        this.commandOrders = commandOrders;
        this.ordersSplitter = ordersSplitter;
        this.verification = verification;
    }

    public void initMapClassMethodHandler() throws NoSuchMethodException, IllegalAccessException {
        logger.info("Start method init. Values CLASS_HANDLES_BUN: {}. Values CLASS_HANDLES_CUTLET: {}.", CLASS_HANDLES_BUN.values(), CLASS_HANDLES_CUTLET.values());
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodType mt = MethodType.methodType(List.class, int.class, CommandOrders.class);
        for (Method m : ordersSplitter.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(KitchenBun.class)) {
                KitchenBun kitchen = m.getAnnotation(KitchenBun.class);

                MethodHandle handle = publicLookup.findVirtual(kitchen.ordersClass(), m.getName(), mt);
                MethodHandle handleBind = handle.bindTo(ordersSplitter);
                MethodHandleBun methodHandleBun = new MethodHandleBun(handleBind, kitchen.kindFlour(), kitchen.kindDough());

                CLASS_HANDLES_BUN.put(kitchen.productClass().getName(), methodHandleBun);
            } else {
                if (m.isAnnotationPresent(KitchenCutlet.class)) {
                    KitchenCutlet kitchenCutlet = m.getAnnotation(KitchenCutlet.class);

                    MethodHandle handle = publicLookup.findVirtual(kitchenCutlet.ordersClass(), m.getName(), mt);
                    MethodHandle handleBind = handle.bindTo(ordersSplitter);
                    MethodHandleCutlet methodHandleCutlet = new MethodHandleCutlet(handleBind, kitchenCutlet.kindMeat(), kitchenCutlet.fatMeat());
                    CLASS_HANDLES_CUTLET.put(kitchenCutlet.productClass().getName(), methodHandleCutlet);
                }
            }
        }
        logger.info("End method init. Values CLASS_HANDLES_BUN: {}. Values CLASS_HANDLES_CUTLET: {}.", CLASS_HANDLES_BUN.values(), CLASS_HANDLES_CUTLET.values());
    }

    public List<Product> makeOrders(int quantity, Class<?> clazz) throws Throwable {

        List<Product> result = new ArrayList<>();
        if (clazz.getSuperclass().equals(Bun.class)) {
            MethodHandleBun methodHandleBun = CLASS_HANDLES_BUN.get(clazz.getName());
            MethodHandle methodHandle = methodHandleBun.handle;
            result = (List<Product>) methodHandle.invokeExact(quantity, commandOrders);
            Bun product = (Bun) result.get(MainUtility.randomInt(0, result.size() - 1));
            verification.verificationBun(product, methodHandleBun.kindFlour, methodHandleBun.kindDough);
        } else {
            if (clazz.getSuperclass().equals(Cutlet.class)) {
                MethodHandleCutlet methodHandleCutlet = CLASS_HANDLES_CUTLET.get(clazz.getName());
                MethodHandle handle = methodHandleCutlet.handle;
                result = (List<Product>) handle.invokeExact(quantity, commandOrders);

                Cutlet product = (Cutlet) result.get(MainUtility.randomInt(0, result.size() - 1));
                verification.verificationCutlet(product, methodHandleCutlet.kindMeat, methodHandleCutlet.fatMeat);
            }
        }
        return result;
    }

    private class MethodHandleBun {

        private MethodHandle handle;
        KindFlour kindFlour;
        KindDough kindDough;
        private MethodHandleBun(MethodHandle handle, KindFlour kindFlour, KindDough kindDough) {
            this.handle = handle;
            this.kindFlour = kindFlour;
            this.kindDough = kindDough;
        }

    }
    private class MethodHandleCutlet {

        private MethodHandle handle;
        KindMeat kindMeat;
        FatMeat fatMeat;
        private MethodHandleCutlet(MethodHandle handle, KindMeat kindMeat, FatMeat fatMeat) {
            this.handle = handle;
            this.kindMeat = kindMeat;
            this.fatMeat = fatMeat;
        }

    }
}
