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
 *
 *  Returns All orders of Client by it Id
 */
//@WebServlet(urlPatterns = "/ajax/orders",name = "AuthOrd")
public class AjaxOrdersServlet extends HttpServlet {

    private OrderService service = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(df);
        resp.setContentType("application/json");
        String str =  req.getParameter("clientId");
        try{
            objectMapper.writeValue(resp.getOutputStream(),service.getOrdersByClientId(Integer.parseInt(str)));
        }
        catch (NumberFormatException e){
            resp.sendRedirect("getitems");
        }

    }
}
