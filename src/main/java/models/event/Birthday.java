package models.event;

import java.util.List;
import java.util.Objects;

import static models.event.EventType.BIRTHDAY;

public class Birthday extends Event {

    private String firstName;
    private String gift;

    public Birthday() {
        super(BIRTHDAY);
    }

    @Override
    public List<String> getAttributes() {
        return List.of(
                "Имя: %s;".formatted(firstName),
                "Подарок: %s".formatted(gift)
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Birthday birthday)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        return Objects.equals(firstName, birthday.firstName) &&
                Objects.equals(gift, birthday.gift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, gift);
    }
}
