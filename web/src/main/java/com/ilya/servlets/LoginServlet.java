package com.ilya.servlets;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ilya on 08.09.2016.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private ClientService service = new ClientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = service.logIn(req.getParameter("email"),req.getParameter("password"));
        if(client==null)resp.sendError(401);
        else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedClient",client);
            resp.sendRedirect("getitems");
        }
    }
}
