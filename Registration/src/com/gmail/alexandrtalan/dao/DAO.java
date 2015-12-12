package com.gmail.alexandrtalan.dao;

import java.util.List;

public interface DAO<T> {

    T create(T entity);

    T read(int id);

    void update(T entity);

    void delete(int id);

    List<T> getAll();
}
