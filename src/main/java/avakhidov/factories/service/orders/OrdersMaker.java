package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenFreezerAspect;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.MainIngredient;
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

    private static final Map<String, MethodHandle> SPLITTER_HANDLES = new TreeMap<>();

    private final CommandOrders commandOrders;
    private final OrdersSplitter ordersSplitter;

    public OrdersMaker(CommandOrders commandOrders, OrdersSplitter ordersSplitter) {
        this.commandOrders = commandOrders;
        this.ordersSplitter = ordersSplitter;
    }

    public void init() throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodType mt = MethodType.methodType(List.class, List.class);

        for (Method m : ordersSplitter.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(KitchenFreezerAspect.class)) {
                KitchenFreezerAspect kitchen = m.getAnnotation(KitchenFreezerAspect.class);

                MethodHandle handle = publicLookup.findVirtual(kitchen.ordersClass(), m.getName(), mt);
                MethodHandle handleBind = handle.bindTo(ordersSplitter);
                SPLITTER_HANDLES.put(kitchen.mainIngredientEnum(), handleBind);
            }
        }
    }

    public List<Product> makeProductList(int quantity, MainIngredient mainIngredient) throws Throwable {
        List<Product> result = new ArrayList<>();

        if (mainIngredient.getMainIngredient().equals(MainIngredientEnum.MEAT)) {
            Meat meat = (Meat) mainIngredient;
            KindMeat kindMeat = meat.getKindMeat();
            if (kindMeat.equals(KindMeat.CHICKEN)) {
                for (int i = 0; i < quantity; i++) {
                    commandOrders.createOderChickenCutlet();
                }
            }
            List<Product> values = new ArrayList<>(commandOrders.getOrders().values());

            MethodHandle methodHandle = SPLITTER_HANDLES.get(MainIngredientEnum.MEAT.name());
            result = (List) methodHandle.invokeExact(values);
        }
        return result;
    }
}
