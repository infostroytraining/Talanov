package com.gmail.alexandrtalan.dao;


import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    Connection getConnection() throws SQLException;
    UserDAO getUserDAO(Connection connection);
}
