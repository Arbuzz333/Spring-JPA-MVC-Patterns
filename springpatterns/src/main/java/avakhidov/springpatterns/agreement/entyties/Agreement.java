package avakhidov.springpatterns.agreement.entyties;

import java.util.Date;




public class Agreement {

    public static final String mortgage = "mortgage_";
    public static final String car_loan = "car_loan_";
    public static final String common = "common_";

    private String number;
    private String owner;
    private Date createDate;
    private Payment payment;

    public Agreement(String number, String owner, Date createDate, Payment payment) {
        this.number = number;
        this.owner = owner;
        this.createDate = createDate;
        this.payment = payment;
    }

    public Agreement(String number, String owner, Payment payment) {
        this.number = number;
        this.owner = owner;
        this.createDate = new Date();
        this.payment = payment;
    }

    public String getNumber() {
        return number;
    }


    public String getOwner() {
        return owner;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public Payment getPayment() {
        return payment;
    }

}
