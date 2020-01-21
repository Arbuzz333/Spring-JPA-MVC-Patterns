package avakhidov.factories.comand;

import avakhidov.factories.bakery.BakeryHouseFSM;

public class CommandBakeryHouseWork implements CommandBakeryHouseState {

    private BakeryHouseFSM bakeryHouseFSM;

    public CommandBakeryHouseWork(BakeryHouseFSM bakeryHouseFSM) {
        this.bakeryHouseFSM = bakeryHouseFSM;
    }

    @Override
    public void executeBakeryHouseState() {
        bakeryHouseFSM.workState();
    }
}
