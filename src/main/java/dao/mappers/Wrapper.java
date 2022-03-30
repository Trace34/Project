package dao.mappers;

import models.BaseModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface Wrapper<T extends BaseModel> {

    void wrap(PreparedStatement preparedStatement, T object) throws SQLException;
}
