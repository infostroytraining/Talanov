package com.gmail.alexandrtalan.dao.storage;


import com.gmail.alexandrtalan.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStorage {

    private Map<Integer, User> storage;
    private AtomicInteger id = new AtomicInteger();

    public UserStorage() {
        storage = new HashMap<>();
    }

    public User add(User user) {
        int id = generateId();
        user.setId(id);
        storage.put(id, user);
        return user;
    }

    public List<User> all() {
        return new ArrayList<User>(storage.values());
    }

    private int generateId() {
        return id.incrementAndGet();
    }

}
