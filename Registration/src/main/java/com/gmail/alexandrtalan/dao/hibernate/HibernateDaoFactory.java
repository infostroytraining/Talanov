package com.gmail.alexandrtalan.dao.hibernate;


import com.gmail.alexandrtalan.dao.DaoFactory;
import com.gmail.alexandrtalan.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class HibernateDaoFactory implements DaoFactory {

    @Override
    public Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new HibernateUserDao();
    }
}
