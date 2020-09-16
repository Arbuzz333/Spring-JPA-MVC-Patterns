package avakhidov.springpatterns.contract.services;

import org.springframework.beans.factory.annotation.Autowired;




public class BaseFactory<T> {

    @Autowired
    private Contract<T> contract;

    public  BaseFactory(Contract<T> contract) {
        this.contract = contract;
    }

    public BaseFactory() {

    }

    public Contract<T> getContract() {
        return contract;
    }
}
