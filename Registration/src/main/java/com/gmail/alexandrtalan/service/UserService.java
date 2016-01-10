package com.gmail.alexandrtalan.service;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.entity.User;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Integer create(User entity) throws SQLException {
        return userDAO.create(entity).getId();
    }

    public boolean isEmailDuplicate(User entity) throws SQLException {
        return userDAO.isEmailDuplicate(entity);
    }
}
