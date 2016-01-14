package com.gmail.alexandrtalan.web.listener;


import com.gmail.alexandrtalan.dao.DaoFactory;
import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.hibernate.HibernateDaoFactory;
import com.gmail.alexandrtalan.service.UserService;
import com.gmail.alexandrtalan.util.PropertyReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final String PATH_TO_PROPERTY_FILE = "C:\\Users\\alexa\\Desktop\\Talanov\\Registration\\src\\main\\resources\\config.properties";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        DaoFactory daoFactory = new HibernateDaoFactory();
        UserDAO userDAO = daoFactory.getUserDAO(null);
        UserService userService = new UserService(userDAO);
        servletContextEvent.getServletContext().setAttribute("userService", userService);

        try {
            Properties properties = PropertyReader.getInstance(PATH_TO_PROPERTY_FILE);
            servletContextEvent.getServletContext().setAttribute("properties", properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
