package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenFreezerAspect;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.Product;
import avakhidov.factories.utility.MainUtility;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrdersMaker {

    private final Map<String, MethodHandle> CLASS_HANDLES = new TreeMap<>();

    private final CommandOrders commandOrders;
    private final OrdersSplitter ordersSplitter;

    public OrdersMaker(CommandOrders commandOrders, OrdersSplitter ordersSplitter) {
        this.commandOrders = commandOrders;
        this.ordersSplitter = ordersSplitter;
    }

    public void initMapClassMethodHandler() throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodType mt = MethodType.methodType(List.class, int.class, CommandOrders.class);
        for (Method m : ordersSplitter.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(KitchenFreezerAspect.class) && m.getParameterCount() == 2) {
                KitchenFreezerAspect kitchen = m.getAnnotation(KitchenFreezerAspect.class);

                MethodHandle handle = publicLookup.findVirtual(kitchen.ordersClass(), m.getName(), mt);
                MethodHandle handleBind = handle.bindTo(ordersSplitter);
                CLASS_HANDLES.put(kitchen.productClass().getName(), handleBind);
            }
        }
    }

    public List<Product> makeOrders(int quantity, Class<?> clazz) throws Throwable {
        List<Product> result;

        MethodHandle methodHandle = CLASS_HANDLES.get(clazz.getName());
        result = (List<Product>) methodHandle.invokeExact(quantity, commandOrders);

        Product product = result.get(MainUtility.randomInt(0, result.size() - 1));

        return result;
    }

}
