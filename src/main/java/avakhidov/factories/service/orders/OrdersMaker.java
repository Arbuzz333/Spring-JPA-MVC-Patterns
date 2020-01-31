package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenFreezerAspect;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.MainIngredient;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Map<String, Method> splitters = new TreeMap<>();

    @Autowired
    private CommandOrders commandOrders;

    @Autowired
    OrdersSplitter ordersSplitter;

    public void init() {
        for (Method m : ordersSplitter.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(KitchenFreezerAspect.class)) {
                KitchenFreezerAspect kitchen = m.getAnnotation(KitchenFreezerAspect.class);
                splitters.put(kitchen.mainIngredientEnum(), m);
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
            MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
            MethodType mt = MethodType.methodType(List.class, List.class);
            MethodHandle handle = publicLookup.findVirtual(OrdersSplitter.class, "getProductListCutlet", mt);
            Object invoke = handle.invoke(commandOrders.getOrders().values());
            result = (List<Product>) handle.invoke(commandOrders.getOrders().values());
        }
        return result;
    }
}
