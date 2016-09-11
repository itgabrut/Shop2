package com.ilya.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.service.OrderService;
import com.ilya.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ilya on 09.09.2016.
 */
@WebServlet(urlPatterns = "/ajax/orders",name = "Ajax_Orders")
public class AjaxOrdersServlet extends HttpServlet {

    OrderService service = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(df);
        resp.setContentType("application/json");
        assert req.getParameter("clientId")!=null;
        try{
            objectMapper.writeValue(resp.getOutputStream(),service.getOrdersByClientId(Integer.parseInt(req.getParameter("clientId"))));
        }
        catch (NumberFormatException e){
            resp.sendRedirect("getitems");
        }

    }
}
