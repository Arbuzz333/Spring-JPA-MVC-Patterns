package avakhidov.springpatterns.agreement.services.commonprocess;

import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.storage.PriorityLimit;
import avakhidov.springpatterns.agreement.storage.StorageAgreementMemory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;



public class StorageAgreementCommonMemory extends StorageAgreementMemory implements PriorityLimit<Agreement> {

    public StorageAgreementCommonMemory(String system) {
        super(system);
    }

    @Override
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

    @Override
    public List<Agreement> creatListAgreement() {
        List<Agreement> agreementList = buildAgreements();
        numberAgreement.putAll(agreementList.stream().collect(Collectors.toMap(Agreement::getNumber, a-> a)));
        return agreementList;
    }

    public void fillStartStorage(List<Agreement> agreements) {
        super.numberAgreement.putAll(agreements.stream().collect(Collectors.toMap(Agreement::getNumber, a -> a)));
    }

    private List<Agreement> buildAgreements() {

        String number = "775";
        String numberBig = "5000";
        String owner = "owner_five";
        String ownerSmall = "owner_cmall";
        Agreement one = new Agreement(number, owner, new BigDecimal(555));
        Agreement two = new Agreement("776", "owner_six", new BigDecimal(321));
        Agreement three = new Agreement("777", "owner_seven", new BigDecimal(5000));
        Agreement four = new Agreement("778", "owner_eight", new BigDecimal(4455));
        Agreement cmall = new Agreement(numberBig, ownerSmall, new BigDecimal(7555));

        String numberOne = "771";
        String numberThousand = "1000";
        String ownerOne = "owner_one";
        String ownerBig = "owner_big";
        Agreement oneFirst = new Agreement(numberOne, ownerOne, new BigDecimal(777));
        Agreement twoSecond = new Agreement("772", "owner_two", new BigDecimal(123));
        Agreement threeThird = new Agreement("773", "owner_three", new BigDecimal(555));
        Agreement fourFourth = new Agreement("774", "owner_four", new BigDecimal(4477));
        Agreement big = new Agreement(numberThousand, ownerBig, new BigDecimal(10000));
        return List.of(one, two, three, four, cmall, oneFirst, twoSecond, threeThird, fourFourth, big);
    }
}
