package avakhidov.factories.comand;

import avakhidov.factories.entity.Product;
import avakhidov.factories.service.recipe.bun.CornBunRecipe;
import avakhidov.factories.service.recipe.bun.WheatBunRecipe;
import avakhidov.factories.service.recipe.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.recipe.cutlet.PorkCutletRecipe;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("#{new Double('${weight_cutlet}')}")
    private double weightCutlet;
    @Value("#{new Double('${weight_bun}')}")
    private double weightBun;

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
        CommandChickenCutlet commandChickenCutlet = new CommandChickenCutlet(chickenCutletRecipe, weightCutlet);
        executeCommand(commandChickenCutlet);
        return commandChickenCutlet.getUuid();
    }

    public UUID createOrderCornBun() {
        CommandCornBun commandCornBun = new CommandCornBun(cornBunRecipe, weightBun);
        executeCommand(commandCornBun);
        return commandCornBun.getUuid();
    }
    public UUID createOrderPorkCutlet() {
        CommandPorkCutlet commandPorkCutlet = new CommandPorkCutlet(porkCutletRecipe, weightCutlet);
        executeCommand(commandPorkCutlet);
        return commandPorkCutlet.getUuid();
    }
    public UUID createOrderWheatBun() {
        CommandWheatBun commandPorkCutlet = new CommandWheatBun(wheatBunRecipe, weightBun, LocalTime.now());
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
