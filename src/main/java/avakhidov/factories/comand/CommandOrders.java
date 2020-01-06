package avakhidov.factories.comand;

import avakhidov.factories.entity.Product;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CommandOrders {

    private final ChickenCutletRecipe chickenCutletRecipe;
    private final CornBunRecipe cornBunRecipe;
    private final WheatBunRecipe wheatBunRecipe;
    private final PorkCutletRecipe porkCutletRecipe;

    private final static double WEIGHT_CUTLET = 0.125;
    private final static double WEIGHT_BUN = 0.075;

    private Map<UUID, Product> orders = new HashMap<>();

    public CommandOrders(
            ChickenCutletRecipe chickenCutletRecipe
            , CornBunRecipe cornBunRecipe
            , WheatBunRecipe wheatBunRecipe
            , PorkCutletRecipe porkCutletRecipe) {
        this.chickenCutletRecipe = chickenCutletRecipe; this.cornBunRecipe = cornBunRecipe;
        this.wheatBunRecipe = wheatBunRecipe; this.porkCutletRecipe = porkCutletRecipe;
    }

    public UUID createOderChickenCutlet() {
        CommandChickenCutlet commandChickenCutlet = new CommandChickenCutlet(chickenCutletRecipe, WEIGHT_CUTLET);
        executeCommand(commandChickenCutlet);
        return commandChickenCutlet.getUuid();
    }

    public UUID createOrderCornBun() {
        CommandCornBun commandCornBun = new CommandCornBun(cornBunRecipe, WEIGHT_BUN);
        executeCommand(commandCornBun);
        return commandCornBun.getUuid();
    }
    public UUID createOrderPorkCutlet() {
        CommandPorkCutlet commandPorkCutlet = new CommandPorkCutlet(porkCutletRecipe, WEIGHT_CUTLET);
        executeCommand(commandPorkCutlet);
        return commandPorkCutlet.getUuid();
    }
    public UUID createOrderWheatBun() {
        CommandWheatBun commandPorkCutlet = new CommandWheatBun(wheatBunRecipe, WEIGHT_BUN, LocalTime.now());
        executeCommand(commandPorkCutlet);
        return commandPorkCutlet.getUuid();
    }
    private void executeCommand(CommandRecipe commandRecipe) {
        Product product = commandRecipe.orderProduct();
        orders.put(commandRecipe.getUuid(), product);
    }

    public void undoOrder(UUID uuid) {
        orders.remove(uuid);
    }

    public Map<UUID, Product> getOrders() {
        return orders;
    }
}
