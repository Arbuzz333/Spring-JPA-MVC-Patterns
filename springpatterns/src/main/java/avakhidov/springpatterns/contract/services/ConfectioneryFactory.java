package avakhidov.springpatterns.contract.services;

import avakhidov.springpatterns.contract.entyties.Currency;




public class ConfectioneryFactory<T extends Currency>  extends BaseFactory<T> {

    public ConfectioneryFactory(Contract<T> contract) {
        super(contract);
    }
}
