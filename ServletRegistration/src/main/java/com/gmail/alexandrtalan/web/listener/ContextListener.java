package com.gmail.alexandrtalan.web.listener;


import com.gmail.alexandrtalan.dao.UserDAO;
import com.gmail.alexandrtalan.dao.memory.MemoryUserDAO;
import com.gmail.alexandrtalan.dao.storage.UserStorage;
import com.gmail.alexandrtalan.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UserStorage storage = new UserStorage();
        UserDAO userDAO = new MemoryUserDAO(storage);
        UserService userService = new UserService(userDAO);
        servletContextEvent.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // we need to add log there
    }
}
