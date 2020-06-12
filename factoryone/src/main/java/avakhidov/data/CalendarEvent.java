package avakhidov.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CalendarEvent extends BaseEvent {

    @DateTimeFormat(pattern = "M/d/yy h:mm")
    private LocalDateTime dateTime;

    public CalendarEvent(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
