package com.gmail.alexandrtalan.dao.memory;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.storage.UserStorage;
import com.gmail.alexandrtalan.entity.User;

import java.sql.SQLException;
import java.util.List;

public class MemoryUserDAO implements UserDAO{

    private UserStorage storage;

    public MemoryUserDAO(UserStorage storage){
        this.storage = storage;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
