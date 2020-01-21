package avakhidov.factories.bakery;

import avakhidov.factories.comand.CommandBakeryHouseState;

public class BakeryCommandExecutor {

    private BakeryHouseFSM bakeryHouseFSM;

    private HistoryBakeryHouse history;

    public BakeryCommandExecutor(BakeryHouseFSM bakeryHouseFSM, HistoryBakeryHouse history) {
        this.bakeryHouseFSM = bakeryHouseFSM;
        this.history = history;
    }

    public void execute(CommandBakeryHouseState command) {
        history.push(command, bakeryHouseFSM.new MementoBakeryHouse());
        command.executeBakeryHouseState();
    }

    public boolean undoExecute() {
        return history.undo();
    }

    public boolean redoExecute() {
        return history.redo();
    }

}
