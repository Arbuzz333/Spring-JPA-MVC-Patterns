package avakhidov.springpatterns.agreement.storage;


import org.springframework.stereotype.Service;

import java.util.Optional;




@Service
public interface StorageAgreement<K, A> {

    A getAgreement(K number);

    void saveAgreement(A agreement);

    Optional<A> findAgreement(K owner);
}
