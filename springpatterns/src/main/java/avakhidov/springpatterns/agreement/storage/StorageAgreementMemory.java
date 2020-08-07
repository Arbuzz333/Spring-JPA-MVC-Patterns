package avakhidov.springpatterns.agreement.storage;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.util.Optional;
import java.util.TreeMap;




public class StorageAgreementMemory implements StorageAgreement<String, Agreement> {

    private TreeMap<String, Agreement> storage = new TreeMap<>();
    private final String system;

    public StorageAgreementMemory(String system) {
        this.system = system;
    }

    @Override
    public Agreement getAgreement(String number) {
        if (number.startsWith(system)) {
            return storage.get(number);
        }
        throw new UnsupportedOperationException(number);
    }

    @Override
    public void saveAgreement(Agreement agreement) {
        if (storage.containsKey(agreement.getNumber()) && !agreement.getNumber().startsWith(system)) {
            throw new UnsupportedOperationException(agreement.getNumber());
        }
        storage.put(agreement.getNumber(), agreement);
    }

    @Override
    public Optional<Agreement> findAgreement(String owner) {
        return storage.values()
                .stream()
                .filter(entry -> entry.getOwner().equals(owner))
                .filter(entry -> entry.getNumber().startsWith(system))
                .findFirst();
    }
}
