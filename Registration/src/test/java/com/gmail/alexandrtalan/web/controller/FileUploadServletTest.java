package com.gmail.alexandrtalan.web.controller;

import com.gmail.alexandrtalan.util.FileUploader;
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
import java.util.Properties;

@RunWith(MockitoJUnitRunner.class)
public class FileUploadServletTest extends Mockito{

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext context;
    @Mock
    private Properties properties;
    @Mock
    private PrintWriter writer;
    @Mock
    private FileUploader fileUploader;

    @Before
    public void before() throws IOException, ServletException {
        when(request.getContentType()).thenReturn("multipart/form-data");
        when(context.getAttribute("properties")).thenReturn(properties);
        when(response.getWriter()).thenReturn(writer);
        new FileUploadServlet().doPost(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        verify(writer, atLeastOnce()).write(anyString());
    }
}