package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{

    boolean save(T dto) throws SQLException;
    boolean update(T dto) throws SQLException;
    boolean remove(String id) throws SQLException;
    T search(String id) throws SQLException;
    List<T> getAll() throws SQLException;

}
