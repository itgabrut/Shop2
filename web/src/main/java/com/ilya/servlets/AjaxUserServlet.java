package com.ilya.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ilya on 26.08.2016.
 */
@WebServlet(urlPatterns = "/ajax/users")
public class AjaxUserServlet extends HttpServlet {

    private ClientService service = new ClientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        objectMapper.setDateFormat(df);
        resp.setContentType("application/json");
        String id;
        if((id=req.getParameter("id"))==null)
        objectMapper.writeValue(resp.getOutputStream(),service.getAll());
        else objectMapper.writeValue(resp.getOutputStream(),service.getClient(Integer.parseInt(id)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service.deleteClient(Integer.parseInt(req.getParameter("id")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        objectMapper.setDateFormat(df);
        String json;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        while((json=bufferedReader.readLine())!=null){
            sb.append(json);
        }
        Client client = objectMapper.readValue(sb.toString(),Client.class);
        service.addClient(client);
    }
}
