package avakhidov.springpatterns.agreement.storage;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import java.util.Optional;
import java.util.TreeMap;




public class StorageAgreementMemory implements StorageAgreement<String, Agreement> {

    protected TreeMap<String, Agreement> numberAgreement = new TreeMap<>();

    private final String system;

    public StorageAgreementMemory(String system) {
        this.system = system;
    }

    @Override
    public Agreement getAgreement(String number) {
        if (number.startsWith(system)) {
            return numberAgreement.get(number);
        }
        throw new UnsupportedOperationException(number);
    }

    @Override
    public void saveAgreement(Agreement agreement) {
        if (numberAgreement.containsKey(agreement.getNumber()) && !agreement.getNumber().startsWith(system)) {
            throw new UnsupportedOperationException(agreement.getNumber());
        }
        numberAgreement.put(agreement.getNumber(), agreement);
    }

    @Override
    public Optional<Agreement> findAgreement(String owner) {
        return numberAgreement.values()
                .stream()
                .filter(entry -> entry.getOwner().equals(owner))
                .filter(entry -> entry.getNumber().startsWith(system))
                .findFirst();
    }
}
