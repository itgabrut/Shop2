package com.ilya.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ilya on 09.09.2016.
 *
 * Process requests from Authorization fllter to pages that display information about orders of specific Client
 */
@WebServlet(urlPatterns = "/orders",name = "Orders")
public class OrdersServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("clientId");
        if(str == null){
            resp.sendRedirect("getitems");
            return;
        }
        req.setAttribute("clientId",str);
        req.getRequestDispatcher("WEB-INF/jsp/Orders.jsp").forward(req,resp);
    }

}
