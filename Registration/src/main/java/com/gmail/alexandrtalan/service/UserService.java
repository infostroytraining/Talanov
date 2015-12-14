package com.gmail.alexandrtalan.service;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.entity.User;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Integer create(User entity){
        return userDAO.create(entity).getId();
    }
}
