package com.gmail.alexandrtalan.service;


import com.gmail.alexandrtalan.dao.UserDAO;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
