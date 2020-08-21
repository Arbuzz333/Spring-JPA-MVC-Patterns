package avakhidov.springpatterns.agreement.entyties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Payment {

    private BigDecimal sum;
    private Date createDate;
    private List<Payment> payingList;

    public Payment(BigDecimal sum, Date createDate) {
        this.sum = sum;
        this.createDate = createDate;
        payingList = new ArrayList<>();
    }

    public Payment(BigDecimal sum) {
        this.sum = sum;
        this.createDate = new Date();
        payingList = new ArrayList<>();
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public List<Payment> getPayingList() {
        return payingList;
    }

    public void setPaymentToList(BigDecimal sum) {
        payingList.add(new Payment(sum, new Date()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getSum().equals(payment.getSum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSum());
    }
}
