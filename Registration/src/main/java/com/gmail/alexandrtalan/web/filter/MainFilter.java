package com.gmail.alexandrtalan.web.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.alexandrtalan.dto.UserDTO;
import nl.captcha.Captcha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/index", "/registration"})
public class MainFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(MainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter init.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if ("POST".equals(request.getMethod())) {
            ObjectMapper mapper = new ObjectMapper();
            String json = request.getParameter("json");
            UserDTO userDTO = mapper.readValue(json, UserDTO.class);

            Captcha captcha = (Captcha) request.getSession().getAttribute("captcha");
            Map<String, String> errors = validate(userDTO, captcha);
            if (!errors.isEmpty()) {
                //debugLogger.debug("Validation failed");
                errors.put("status", "Error");
                resp.getWriter().write(mapper.writeValueAsString(errors));
            } else {
                //debugLogger.debug("Validation success");
                req.getServletContext().setAttribute("userDTO", userDTO);
                filterChain.doFilter(req, resp);
            }
        } else {
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }

    private Map<String, String> validate(UserDTO userDTO, Captcha captcha) {
        Map<String, String> errors = new HashMap<>();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();

        if (!captcha.isCorrect(userDTO.getCaptcha())) {
            //debugLogger.debug("The entered value does not match the value in the picture.");
            errors.put("captcha", "The entered value does not match the value in the picture.");
        }

        if (firstName == null || firstName.isEmpty()) {
            //debugLogger.debug("Field first name must be entered.");
            errors.put("firstName", "Field first name must be entered.");
        }

        if (lastName == null || lastName.isEmpty()) {
            //debugLogger.debug("Field last name must be entered.");
            errors.put("lastName", "Field last name must be entered.");
        }

        if (email != null && !email.isEmpty()) {
            Matcher checkEmail = Pattern.compile(".+@.+\\..+").matcher(email);
            if (!checkEmail.find()) {
                // debugLogger.debug("E-mail is not correct.");
                errors.put("email", "E-mail is not correct.");
            }
        } else {
            //debugLogger.debug("Field e-mail must be entered.");
            errors.put("email", "Field e-mail must be entered.");
        }

        if (password != null && !password.isEmpty()) {
            Matcher checkPassword = Pattern.compile("^[a-zA-Z0-9]{6,18}$").matcher(password);
            if (!checkPassword.find()) {
                //  debugLogger.debug("Password must contain letters [a-Z], numbers [0-9] and have a length [6 - 18].");
                errors.put("password", "Password must contain letters [a-Z], numbers [0-9] and have a length [6 - 18].");
            }
        } else {
            // debugLogger.debug("Field password must be entered.");
            errors.put("password", "Field password must be entered.");
        }


        return errors;
    }

    @Override
    public void destroy() {
    }
}
