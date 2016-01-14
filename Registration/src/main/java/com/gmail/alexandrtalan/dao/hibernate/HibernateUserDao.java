package com.gmail.alexandrtalan.dao.hibernate;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.entity.User;
import com.gmail.alexandrtalan.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class HibernateUserDao implements UserDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @Override
    public User create(User entity) throws SQLException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public User read(int id) throws SQLException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        User user = (User) session.createQuery("from User where id = :id").setInteger("id", id).uniqueResult();
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void update(User entity) throws SQLException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) throws SQLException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("delete User where id = :id").setInteger("id", id).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public boolean isEmailDuplicate(User entity) throws SQLException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where email = :email");
        query.setString("email", entity.getEmail());
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();

        return user != null;
    }
}
