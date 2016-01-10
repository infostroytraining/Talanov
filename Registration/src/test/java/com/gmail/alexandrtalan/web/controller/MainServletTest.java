package com.gmail.alexandrtalan.web.controller;

import com.gmail.alexandrtalan.dto.UserDTO;
import com.gmail.alexandrtalan.entity.User;
import com.gmail.alexandrtalan.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
public class MainServletTest extends Mockito {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;
    @Mock
    private ServletContext servletContext;
    @Mock
    private UserDTO userDTO;
    @Mock
    private PrintWriter writer;

    @Before
    public void before() throws IOException{
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userService")).thenReturn(userService);
        when(request.getServletContext().getAttribute("userDTO")).thenReturn(userDTO);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testContext() throws IOException, ServletException {
        new MainServlet().doPost(request, response);
        verify(servletContext, atLeast(1)).getAttribute("userService");
        verify(servletContext, atLeast(1)).getAttribute("userDTO");
    }

    @Test
    public void testNotValid() throws IOException, SQLException, ServletException {
        when(userService.isEmailDuplicate(any(User.class))).thenReturn(false);
        new MainServlet().doPost(request, response);
        verify(response.getWriter(), atLeast(1)).write(anyString());
    }

    @Test
    public void testSQLException() throws SQLException, ServletException, IOException {
        doThrow(new SQLException()).when(userService).isEmailDuplicate(any(User.class));
        new MainServlet().doPost(request, response);
    }

    @Test
    public void testValidation() throws SQLException, ServletException, IOException {
        when(userService.isEmailDuplicate(any(User.class))).thenReturn(true);
        new MainServlet().doPost(request, response);
        verify(response.getWriter(), atLeast(1)).write(anyString());
    }

}