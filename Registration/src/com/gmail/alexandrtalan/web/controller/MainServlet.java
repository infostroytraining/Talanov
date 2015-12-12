package com.gmail.alexandrtalan.web.controller;

import com.gmail.alexandrtalan.dto.UserDTO;
import com.gmail.alexandrtalan.entity.User;
import com.gmail.alexandrtalan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDTO userDTO = new UserDTO(firstName, lastName, email, password);

        Map<String, String> errors = validate(userDTO);
        if(errors.size() == 0){
            req.setAttribute("success", "Registration complete!");
            userService.create(new User(firstName, lastName, email, password));
        } else {
            req.setAttribute("userDTO", userDTO);
            req.setAttribute("errors", errors);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private Map<String, String> validate(UserDTO userDTO){
        Map<String, String> errors = new HashMap<>();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        if(email != null && !email.isEmpty()) {
            Matcher checkEmail = Pattern.compile(".+@.+\\..+").matcher(email);
            if (!checkEmail.find()) {
                errors.put("emailFail", "E-mail is not correct.");
            }
        } else{
            errors.put("emailFail", "Field e-mail must be entered.");
        }

        if(password != null && !password.isEmpty()) {
            Matcher checkPassword = Pattern.compile("^[a-zA-Z0-9]{6,18}$").matcher(password);
            if (!checkPassword.find()) {
                errors.put("passwordFail", "Password must contain letters [a-Z], numbers [0-9] and have a length [6 - 18].");
            }
        } else {
            errors.put("passwordFail", "Field password must be entered.");
        }

        return errors;
    }
}