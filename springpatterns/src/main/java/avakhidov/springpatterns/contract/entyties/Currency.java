package avakhidov.springpatterns.contract.entyties;



public class Currency {

    private CurrencyType currencyType = CurrencyType.RUB;

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public enum  CurrencyType {

        RUB,
        DOLLAR,
        EUR
    }
}
