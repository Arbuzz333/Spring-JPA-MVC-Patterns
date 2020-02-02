package avakhidov.factories.comand;

import avakhidov.factories.bakery.BakeryHouseFSM;

public class CommandBakeryHouseDowntime implements CommandBakeryHouseState {

    private BakeryHouseFSM bakeryHouseFSM;

    public CommandBakeryHouseDowntime(BakeryHouseFSM bakeryHouseFSM) {
        this.bakeryHouseFSM = bakeryHouseFSM;
    }

    @Override
    public void executeBakeryHouseState() {
        bakeryHouseFSM.downtimeState();
    }
}
