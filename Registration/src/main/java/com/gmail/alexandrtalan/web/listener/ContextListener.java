package com.gmail.alexandrtalan.web.listener;


import com.gmail.alexandrtalan.dao.DaoFactory;
import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.memory.MemoryDaoFactory;
import com.gmail.alexandrtalan.service.UserService;
import com.gmail.alexandrtalan.util.PropertyReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final String PATH_TO_PROPERTY_FILE = "C:\\Users\\alexa\\Desktop\\Talanov\\Registration\\src\\main\\resources\\config.properties";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DaoFactory daoFactory = new MemoryDaoFactory();
            Connection connection = daoFactory.getConnection();
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            UserService userService = new UserService(userDAO);
            servletContextEvent.getServletContext().setAttribute("userService", userService);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
