package dao.repositories;

import dao.connection.ConnectionManager;
import dao.mappers.Extractor;
import dao.mappers.Wrapper;
import models.event.Birthday;
import models.event.Event;
import models.event.EventType;
import models.event.Meeting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Repository<Event> {

    private final ConnectionManager connectionManager;
    private final Extractor<Event> extractor;
    private final Wrapper<Event> wrapper;

    public EventRepository() {
        connectionManager = ConnectionManager.getInstance();

        extractor = rs -> {
            List<Event> events = new ArrayList<>();

            while (rs.next()) {
                EventType type = EventType.valueOf(rs.getString(2));
                Event event = EventType.getInstance(type);

                event.setId(rs.getLong(1));
                event.setDate(rs.getDate(3).toLocalDate());
                event.setTime(rs.getTime(4).toLocalTime());
                event.setDescription(rs.getString(5));

                switch (type) {
                    case BIRTHDAY -> {
                        Birthday birthday = (Birthday) event;

                        birthday.setFirstName(rs.getString(6));
                        birthday.setGift(rs.getString(9));
                    }

                    case MEETING -> {
                        Meeting meeting = (Meeting) event;

                        meeting.setFirstName(rs.getString(6));
                        meeting.setLastName(rs.getString(7));
                        meeting.setAge(rs.getInt(8));
                    }

                    default -> throw new IllegalArgumentException();
                }

                events.add(event);
            }

            return events;
        };

        wrapper = (ps, o) -> {
            ps.setString(1, o.getType().toString());
            ps.setDate(2, Date.valueOf(o.getDate()));
            ps.setTime(3, Time.valueOf(o.getTime()));
            ps.setString(4, o.getDescription());

            switch (o.getType()) {
                case BIRTHDAY -> {
                    Birthday birthday = (Birthday) o;

                    ps.setString(5, birthday.getFirstName());
                    ps.setString(6, null);
                    ps.setInt(7, -1);
                    ps.setString(8, birthday.getGift());
                }

                case MEETING -> {
                    Meeting meeting = (Meeting) o;

                    ps.setString(5, meeting.getFirstName());
                    ps.setString(6, meeting.getLastName());
                    ps.setInt(7, meeting.getAge());
                    ps.setString(8, null);
                }

                default -> throw new IllegalArgumentException();
            }
        };
    }

    @Override
    public boolean insert(Event object) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into events(type, date, time, description, " +
                            "first_name, last_name, age, gift) values (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            wrapper.wrap(preparedStatement, object);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Event find(long id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from events where id=?"
            );

            preparedStatement.setLong(1, id);

            List<Event> extracted = extractor.extract(preparedStatement.executeQuery());

            return extracted.size() > 0 ? extracted.get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Event> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from events"
            );

            return extractor.extract(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(long id, Event object) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update events set type=?, date=?, time=?, description=?, " +
                            "first_name=?, last_name=?, age=?, gift=? where id=?"
            );

            wrapper.wrap(preparedStatement, object);

            preparedStatement.setLong(9, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean remove(long id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from events where id=?"
            );

            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
