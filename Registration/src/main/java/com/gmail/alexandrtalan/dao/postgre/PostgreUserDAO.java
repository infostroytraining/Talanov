package com.gmail.alexandrtalan.dao.postgre;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.entity.User;

import java.sql.*;

public class PostgreUserDAO implements UserDAO {

    private Connection connection;

    public PostgreUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User entity) throws SQLException {
        final String query = "INSERT INTO public.user(email, first_name, last_name, password, image_path) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getImagePath());

        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            Integer Id = resultSet.getInt(1);
            entity.setId(Id);
        } else {
            throw new SQLException();
        }

        return entity;
    }

    @Override
    public User read(int id) throws SQLException {
        final String query = "SELECT * FROM public.user AS u WHERE u.id = ?";
        User user = new User();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            user.setId(id);
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setImagePath(resultSet.getString("image_path"));
        }

        return user;
    }

    @Override
    public void update(User entity) throws SQLException {
        final String query = "UPDATE public.user AS u " +
                "SET u.email = ?, u.first_name = ?, u.last_name = ?, u.password = ?, u.image_path = ?" +
                "WHERE u.id = ?";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getImagePath());
        statement.setInt(6, entity.getId());
    }

    @Override
    public void delete(int id) throws SQLException {
        final String query = "DELETE FROM public.user AS u WHERE u.id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.execute();
    }
}
