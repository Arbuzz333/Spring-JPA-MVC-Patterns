package avakhidov.data;

import java.math.BigDecimal;

public class MoneyEvent extends BaseEvent {

    private BigDecimal money;


    private String description;

    public MoneyEvent(BigDecimal money, String description) {
        this.money = money;
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getDescription() {
        return description;
    }
}
