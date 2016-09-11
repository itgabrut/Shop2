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
 * Created by ilya on 11.09.2016.
 */
@WebServlet(urlPatterns = "/orderstopost")
public class ForOrdersAjaxPost extends HttpServlet {

    OrderService service = new OrderServiceImpl();
    ClientService clientService = new ClientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
        while((json=bufferedReader.readLine())!=null){
            sb.append(json);
        }
        JsonNode node =  objectMapper.readTree(sb.toString()).get("arr");
        Map<Integer,Integer> map = new HashMap<>();
        if(node.isArray()){
            for (JsonNode nn : node){
                map.put(nn.get("itemId").asInt(),nn.get("quantity").asInt());
            }
        }
//        Client current = (Client)req.getSession().getAttribute("loggedClient");
        Client current = clientService.getClient(Integer.parseInt(req.getParameter("clientId")));
        if(current!=null) service.addOrder(map,current);
        else resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        BucketCheckerUtils.clearBucket(req);
    }
}
