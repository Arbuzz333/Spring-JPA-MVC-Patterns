package avakhidov.springpatterns.contract.entyties;


import avakhidov.springpatterns.contract.services.Contract;



public class BuildingContract implements Contract<Currency> {

    @Override
    public Currency getContent() {
        return new Currency();
    }
}
