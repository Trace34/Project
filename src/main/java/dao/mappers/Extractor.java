package dao.mappers;

import models.BaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface Extractor<T extends BaseModel> {

    List<T> extract(ResultSet resultSet) throws SQLException;
}
