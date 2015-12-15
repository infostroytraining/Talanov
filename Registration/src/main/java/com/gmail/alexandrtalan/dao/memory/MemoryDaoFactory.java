package com.gmail.alexandrtalan.dao.memory;


import com.gmail.alexandrtalan.dao.DaoFactory;
import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.storage.UserStorage;

import java.sql.Connection;
import java.sql.SQLException;

public class MemoryDaoFactory implements DaoFactory {

    @Override
    public Connection getConnection() throws SQLException {
        return UserStorage.getInstance();
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new MemoryUserDAO(connection);
    }
}
