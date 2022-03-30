package models.event;

import java.util.function.Supplier;

public enum EventType {

    BIRTHDAY(Birthday::new),
    MEETING(Meeting::new);

    private final Supplier<Event> instantiator;

    EventType(Supplier<Event> instantiator) {
        this.instantiator = instantiator;
    }

    public static Event getInstance(EventType type) {
        return type.instantiator.get();
    }

    @Override
    public String toString() {
        return name();
    }
}
