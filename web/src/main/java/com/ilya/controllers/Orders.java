package com.ilya.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.model.Client;
import com.ilya.model.Item;
import com.ilya.service.ClientService;
import com.ilya.service.ItemService;
import com.ilya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import utils.BucketCheckerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilya on 27.09.2016.
 */
@Controller
@SessionAttributes(value = {"loggedClient","Map"})
public class Orders {

    @Autowired
    ClientService clientService;
    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/orders")
    public String toOrderView(){
        return "Orders";
    }

    @RequestMapping(value = "/adminOrders")
    public String toAdminOrderView(@RequestParam(value = "clientId")String clientId,Model model){
        model.addAttribute("clientId",clientId);
        return "OrdersForAdmin";
    }

    @RequestMapping(value = "/orderstopost",method = RequestMethod.POST)
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
    @RequestMapping(value = "/singleorder",method = RequestMethod.GET)
    public String forSingleOrder(@RequestParam(value = "orderId")String orderId, Model model){
        int loggedClientId = ((Client)model.asMap().get("loggedClient")).getId();
        if(orderService.verifyOrderOnLoggedClient(orderId,loggedClientId)){
            Map<Item,Integer> map = itemService.getItemsAndQuantityByOrder(Integer.parseInt(orderId));
            BucketCheckerUtils.saveListFotosToMemory((Map<String,byte[]>)model.asMap().get("Map"),new ArrayList<>(map.keySet()));
            model.addAttribute("itemsForOrderMap",map);
            return "Order";
        }
        else return "redirect:getitems";
    }
    @RequestMapping(value = "/adminSingleorder",method = RequestMethod.GET)
    public String AdminSingleOrder(@RequestParam("orderId")String orderId,
                                   Model model){
            Map<Item,Integer> map = itemService.getItemsAndQuantityByOrder(Integer.parseInt(orderId));
            BucketCheckerUtils.saveListFotosToMemory((Map<String,byte[]>)model.asMap().get("Map"),new ArrayList<>(map.keySet()));
            model.addAttribute("itemsForOrderMap",map);
            return "Order";
    }
}
