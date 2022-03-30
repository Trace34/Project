package models.event;

import java.util.List;
import java.util.Objects;

import static models.event.EventType.MEETING;

public class Meeting extends Event {

    private String firstName;
    private String lastName;
    private int age;

    public Meeting() {
        super(MEETING);
    }

    @Override
    public List<String> getAttributes() {
        return List.of(
                "Имя: %s;".formatted(firstName),
                "Фамилия: %s;".formatted(lastName),
                "Возраст: %s".formatted(age)
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Meeting meeting)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        return age == meeting.age &&
                Objects.equals(firstName, meeting.firstName) &&
                Objects.equals(lastName, meeting.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, age);
    }
}
