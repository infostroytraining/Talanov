package com.gmail.alexandrtalan.dao.memory;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.storage.UserStorage;
import com.gmail.alexandrtalan.entity.User;

import java.sql.Connection;
import java.util.List;

public class MemoryUserDAO implements UserDAO{

    private UserStorage storage;

    public MemoryUserDAO(Connection storage){
        this.storage = (UserStorage) storage;
    }

    @Override
    public User create(User entity) {
        return storage.add(entity);
    }

    @Override
    public User read(int id) {
        for(User user: storage.all()){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User entity) {
        storage.all().forEach(user -> {
            if(entity.getId().equals(user.getId())){
                user.setFirstName(entity.getFirstName());
                user.setLastName(entity.getLastName());
                user.setEmail(entity.getEmail());
                user.setPassword(entity.getPassword());
            }
        });
    }

    @Override
    public void delete(int id) {
        storage.delete(id);
    }

    @Override
    public List<User> getAll() {
        return storage.all();
    }
}
