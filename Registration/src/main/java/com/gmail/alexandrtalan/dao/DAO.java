package com.gmail.alexandrtalan.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T create(T entity) throws SQLException;

    T read(int id) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(int id) throws SQLException;
}
