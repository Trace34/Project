package models.event;

import models.BaseModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public abstract class Event extends BaseModel {

    private final EventType type;

    private LocalDate date;
    private LocalTime time;
    private String description;

    public Event(EventType type) {
        this.type = type;
    }

    public abstract List<String> getAttributes();

    public EventType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Event event)) {
            return false;
        }

        return type == event.type &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, time, description);
    }
}
