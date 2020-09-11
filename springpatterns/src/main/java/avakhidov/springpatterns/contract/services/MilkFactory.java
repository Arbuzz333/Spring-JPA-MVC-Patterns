package avakhidov.springpatterns.contract.services;


import avakhidov.springpatterns.contract.entyties.Currency;



public class MilkFactory<T extends Currency> extends BaseFactory<T> {

}
