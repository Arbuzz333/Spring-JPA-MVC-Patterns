package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreementMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;



public class StorageAgreementCommonMemory extends StorageAgreementMemory {

    public StorageAgreementCommonMemory(String system) {
        super(system);
    }

    public PriorityQueue<Agreement> getAgreementsLimit(int max) {
        PriorityQueue<Agreement> pq = new PriorityQueue<>();
        if (max == 0) {
            return pq;
        }
        List<Agreement> agreements = new ArrayList<>(numberAgreement.values());
        if (agreements.size() <= max) {
            pq.addAll(agreements);
        } else {
            List<Agreement> subList = agreements.subList(0, max);
            pq.addAll(subList);
            agreements.removeAll(subList);
            for (Agreement agreement : agreements) {
                Agreement peek = pq.peek();
                if (peek.getPayment().getSum().compareTo(agreement.getPayment().getSum()) < 0) {
                    pq.poll();
                    pq.add(agreement);
                }
            }
        }
        return pq;
    }

    public void fillStartStorage(List<Agreement> agreements) {
        super.numberAgreement.putAll(agreements.stream().collect(Collectors.toMap(Agreement::getNumber, a -> a)));
    }
}
