package com.gmail.alexandrtalan.web.controller;

import com.gmail.alexandrtalan.exeption.NotCorrectFormatFileException;
import com.gmail.alexandrtalan.util.FileUploader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(urlPatterns = "/upload_profile_photo")
public class FileUploadServlet extends HttpServlet {

    private static String basicFilePath;
    private static String[] permissibleTypes;

    @Override
    public void init() throws ServletException {
        Properties properties = (Properties) getServletContext().getAttribute("properties");
        basicFilePath = properties.getProperty("basicFilePath");
        permissibleTypes = properties.getProperty("permissibleTypes").split(",\\s+");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/json");

        try {
            String filePath = FileUploader.upload(req, basicFilePath, permissibleTypes);
            resp.getWriter().write("{\"status\":\"Ok\", \"imageUrl\":\"" + filePath + "\"}");
        } catch (NotCorrectFormatFileException e) {
            resp.getWriter().write("{\"status\":\"Error\"");
        }
    }
}
