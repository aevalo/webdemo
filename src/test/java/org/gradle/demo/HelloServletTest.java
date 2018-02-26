package org.gradle.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("fast")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelloServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
	@DisplayName("doGet()")
    public void doGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        new HelloServlet().doGet(request, response);

        assertEquals("Hello, World!", stringWriter.toString());
    }

    @Test
	@DisplayName("doPostWithoutName()")
    public void doPostWithoutName() throws Exception {
        when(request.getRequestDispatcher("response.jsp"))
                .thenReturn(requestDispatcher);

        new HelloServlet().doPost(request, response);

        verify(request).setAttribute("user", "World");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
	@DisplayName("doPostWithName()")
    public void doPostWithName() throws Exception {
        when(request.getParameter("name")).thenReturn("Dolly");
        when(request.getRequestDispatcher("response.jsp"))
                .thenReturn(requestDispatcher);

        new HelloServlet().doPost(request, response);

        verify(request).setAttribute("user", "Dolly");
        verify(requestDispatcher).forward(request, response);
    }
}
