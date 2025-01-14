package veras.fr.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name="helloWorld", urlPatterns ="/hello")
public class Controleur extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/helloworld.jsp")
                .forward(req, res);
    }
}