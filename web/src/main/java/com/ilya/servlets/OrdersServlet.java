package com.ilya.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.service.ClientServiceImpl;
import com.ilya.service.OrderService;
import com.ilya.service.OrderServiceImpl;
import utils.BucketCheckerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilya on 09.09.2016.
 */
@WebServlet(urlPatterns = "/orders",name = "Orders")
public class OrdersServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

             req.getRequestDispatcher("WEB-INF/jsp/Orders.jsp").forward(req,resp);

    }

}
