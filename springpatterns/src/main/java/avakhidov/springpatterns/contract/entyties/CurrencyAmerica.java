package avakhidov.springpatterns.contract.entyties;




public class CurrencyAmerica extends Currency{

    private CurrencyType currencyType = CurrencyType.DOLLAR;

    public CurrencyType getCurrencyType() {
        return currencyType;
    }


}
