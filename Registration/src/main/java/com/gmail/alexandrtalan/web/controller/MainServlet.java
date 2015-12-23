package com.gmail.alexandrtalan.web.controller;

import com.gmail.alexandrtalan.dto.UserDTO;
import com.gmail.alexandrtalan.entity.User;
import com.gmail.alexandrtalan.service.UserService;
import com.gmail.alexandrtalan.util.FileUploader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/index", "/home"})
public class MainServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        Properties properties = (Properties) req.getServletContext().getAttribute("properties");
        String basicFilePath = properties.getProperty("basicFilePath");
        String[] permissibleTypes = properties.getProperty("permissibleTypes").split(",\\s+");

        try {
            UserDTO userDTO = (UserDTO) req.getServletContext().getAttribute("userDTO");
            String imagePath = FileUploader.upload(req, basicFilePath, permissibleTypes);
            userDTO.setImagePath(imagePath);
            int ID = userService.create(new User(userDTO));
            logger.info("User with id => {} added.", ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}