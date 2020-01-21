package avakhidov.factories.bakery;

import avakhidov.factories.comand.CommandBakeryHouseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope("prototype")
public class HistoryBakeryHouse {

    private Map<CommandBakeryHouseState, BakeryHouseFSM.MementoBakeryHouse> commandMemento = new LinkedHashMap<>();

    public void push(CommandBakeryHouseState command, BakeryHouseFSM.MementoBakeryHouse memento) {
        commandMemento.put(command, memento);
    }

    public boolean undo() {
        if (commandMemento.size() < 2) {
            return false;
        }
        Map.Entry<CommandBakeryHouseState, BakeryHouseFSM.MementoBakeryHouse> bakeryMementoLast =
                new ArrayList<>(commandMemento.entrySet()).get(commandMemento.size() - 1);

        bakeryMementoLast.getValue().restoreBakeryHouse();
        commandMemento.remove(bakeryMementoLast.getKey());
        return true;
    }

    public boolean redo() {
        if (commandMemento.isEmpty()) {
            return false;
        }
        Map.Entry<CommandBakeryHouseState, BakeryHouseFSM.MementoBakeryHouse> bakeryMementoLast =
                new ArrayList<>(commandMemento.entrySet()).get(commandMemento.size() - 1);

        bakeryMementoLast.getKey().executeBakeryHouseState();
        return true;
    }

}
