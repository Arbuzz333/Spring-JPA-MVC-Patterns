package avakhidov.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CalendarEvent extends BaseEvent {

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDateTime dateTime;

    public CalendarEvent(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
