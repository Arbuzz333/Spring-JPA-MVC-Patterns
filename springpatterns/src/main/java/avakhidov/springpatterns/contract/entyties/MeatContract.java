package avakhidov.springpatterns.contract.entyties;


import avakhidov.springpatterns.contract.services.Contract;



public class MeatContract implements Contract<CurrencyAmerica> {

    public CurrencyAmerica getContent() {
        return new CurrencyAmerica();
    }
}
