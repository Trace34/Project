package services;

import models.BaseModel;

import java.util.List;

public interface Service<T extends BaseModel> {

    boolean insert(T object);

    T find(long id);

    List<T> findAll();

    boolean update(long id, T object);

    boolean remove(long id);
}
