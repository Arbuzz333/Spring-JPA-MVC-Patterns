package avakhidov.springpatterns.agreement.storage;

import avakhidov.springpatterns.agreement.entyties.Agreement;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.PriorityQueue;




public interface PriorityLimit<A> {

    PriorityQueue<A> getAgreementsLimit(int max);

    @PostConstruct
    List<Agreement> creatListAgreement();
}
