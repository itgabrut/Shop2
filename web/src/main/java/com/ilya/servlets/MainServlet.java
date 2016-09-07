package com.ilya.servlets;

import com.ilya.dao.OrderRepository;
import com.ilya.dao.OrderRepositoryImpl;
import com.ilya.service.ClientService;
import com.ilya.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ilya on 20.08.2016.
 */

@WebServlet(name = "userServlet", urlPatterns = "/clients")
public class MainServlet extends HttpServlet {

    private ClientService service = new ClientServiceImpl();
    private OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("delete".equals(req.getParameter("action"))){
            service.deleteClient(Integer.parseInt(req.getParameter("id")));
        }
        req.setAttribute("clientList",service.getAll());
        req.getRequestDispatcher("Clients.jsp").forward(req,resp);
    }
}
