package avakhidov.springpatterns.agreement.storage;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.util.Optional;
import java.util.TreeMap;




public class StorageMortgageAgreement implements StorageAgreement<String, Agreement> {

    private TreeMap<String, Agreement> storage = new TreeMap<>();

    @Override
    public Agreement getAgreement(String number) {
        return storage.get(number);
    }

    @Override
    public void saveAgreement(Agreement agreement) {
        if (storage.containsKey(agreement.getNumber())) {
            throw new UnsupportedOperationException(agreement.getNumber());
        }
        storage.put(agreement.getNumber(), agreement);
    }

    @Override
    public Optional<Agreement> findAgreement(String owner) {
        return storage.values()
                .stream()
                .filter(entry -> entry.getOwner().equals(owner))
                .findFirst();
    }
}
