package com.ilya.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.BucketCheckerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilya on 27.09.2016.
 */
@Controller
public class Orders {

    @Autowired
    ClientService clientService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders")
    public String toOrderView(){
        return "Orders";
    }

    @RequestMapping(value = "/orderstopost")
    public void postOrder(HttpServletRequest req, HttpServletResponse resp, Model model)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json);
            }
            JsonNode node = objectMapper.readTree(sb.toString()).get("arr");
            Map<Integer, Integer> map = new HashMap<>();
            if (node.isArray()) {
                for (JsonNode nn : node) {
                    map.put(nn.get("itemId").asInt(), nn.get("quantity").asInt());
                }
            }
            int clientId =((Client)model.asMap().get("loggedClient")).getId();
            Client asked = clientService.getClient(clientId);
            if (asked == null || !orderService.addOrder(map, asked)) resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            else BucketCheckerUtils.clearBucket(req);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            resp.sendRedirect("getitems");
        }

    }
}
