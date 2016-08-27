package com.ilya.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.service.ClientService;
import com.ilya.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ilya on 26.08.2016.
 */
@WebServlet(urlPatterns = "/ajax/users")
public class AjaxServlet extends HttpServlet {

    private ClientService service = new ClientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);

        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getOutputStream(),service.getAll());

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service.deleteClient(Integer.parseInt(req.getParameter("Id")));

    }
}
