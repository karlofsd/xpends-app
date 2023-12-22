package com.soberk.xpends.core.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CRUDRepository<T,U> {
    T create(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(U id) throws SQLException;
    T get(U id) throws SQLException;
    List<T> getAll() throws SQLException;
}
