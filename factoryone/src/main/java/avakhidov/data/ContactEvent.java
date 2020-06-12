package avakhidov.data;

public class ContactEvent extends BaseEvent {

    private String name;

    private String phoneNumber;

    public ContactEvent(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
