package com.gmail.alexandrtalan.web.controller;


import com.gmail.alexandrtalan.dto.UserDTO;
import com.gmail.alexandrtalan.entity.User;
import com.gmail.alexandrtalan.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/index", "/registration"})
public class MainServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/json");
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        try {
            UserDTO userDTO = (UserDTO) req.getServletContext().getAttribute("userDTO");
            User user = new User(userDTO);
            if(userService.isEmailDuplicate(user)) {
                int ID = userService.create(user);
                logger.info("User with id => {} added.", ID);
                resp.getWriter().write("{\"status\":\"Ok\"}");
            } else {
                resp.getWriter().write("{\"status\":\"Error\", \"message\":\"Email already exists!\"}");
            }
        } catch (SQLException e) {
            resp.getWriter().write("{\"status\":\"Error\"}");
        }
    }

}