package com.gmail.alexandrtalan.controller;

import com.gmail.alexandrtalan.util.MemoryForLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/server-log-app/logs")
public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String allLogs = MemoryForLog.getAllLogs();
        resp.getWriter().write(allLogs);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryForLog.setLogs(req.getParameter("logEvent"));
    }
}