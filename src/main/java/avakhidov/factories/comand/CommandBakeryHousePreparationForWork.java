package avakhidov.factories.comand;

import avakhidov.factories.bakery.BakeryHouseFSM;

public class CommandBakeryHousePreparationForWork implements CommandBakeryHouseState {

    private BakeryHouseFSM bakeryHouseFSM;

    public CommandBakeryHousePreparationForWork(BakeryHouseFSM bakeryHouseFSM) {
        this.bakeryHouseFSM = bakeryHouseFSM;
    }

    @Override
    public void executeBakeryHouseState() {
        bakeryHouseFSM.preparationForWorkState();
    }
}
