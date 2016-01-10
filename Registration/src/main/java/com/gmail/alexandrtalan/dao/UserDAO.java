package com.gmail.alexandrtalan.dao;


import com.gmail.alexandrtalan.entity.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User> {

    boolean isEmailDuplicate(User entity) throws SQLException;
}
