package services;

import dao.repositories.Repository;
import models.event.Event;

import java.util.List;

public record EventService(Repository<Event> repository) implements Service<Event> {

    @Override
    public boolean insert(Event object) {
        return repository.insert(object);
    }

    @Override
    public Event find(long id) {
        return repository.find(id);
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean update(long id, Event object) {
        return repository.update(id, object);
    }

    @Override
    public boolean remove(long id) {
        return repository.remove(id);
    }
}
