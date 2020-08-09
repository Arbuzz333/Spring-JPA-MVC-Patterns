package avakhidov.springpatterns.agreement.storage;

import java.util.Optional;




public interface StorageAgreement<K, A> {

    A getAgreement(K number);

    void saveAgreement(A agreement);

    Optional<A> findAgreement(K owner);
}
