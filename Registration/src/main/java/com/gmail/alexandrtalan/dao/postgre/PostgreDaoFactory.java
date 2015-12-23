package com.gmail.alexandrtalan.dao.postgre;


import com.gmail.alexandrtalan.dao.DaoFactory;
import com.gmail.alexandrtalan.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDaoFactory implements DaoFactory {

    private final static String user = "postgres";
    private final static  String password = "root";
    private final static  String url = "jdbc:postgresql://localhost:5432/user";
    private final static  String driver = "org.postgresql.Driver";

    public PostgreDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new PostgreUserDAO(connection);
    }
}
