package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.model.Order;
import com.ilya.model.enums_utils.Delivery_status;
import com.ilya.model.enums_utils.Pay_status;
import com.ilya.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 27.09.2016.
 */
@RestController
@RequestMapping(value = "/ajax/orders")
@SessionAttributes("loggedClient")
public class RestOrders {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/get",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(Model model){
        return orderService.getOrdersByClientId(((Client)model.asMap().get("loggedClient")).getId());
    }

    @RequestMapping(value = "/adminGet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAdminOrders(@RequestParam(value = "clientId")String clientId){
        return orderService.getOrdersByClientId(Integer.parseInt(clientId));
    }

    @RequestMapping(value = "/adminGetAll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllSorted(){
        return orderService.getAllSortedDate();
    }

    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public void changeStatus(@RequestParam Map<String,Object> objMap){
//        String s = objMap.get("orderId");
//        Delivery_status status = Delivery_status.valueOf(objMap.get("checkbox1"));
//        Pay_status pay_status = Pay_status.valueOf(objMap.get("checkbox2"));
        orderService.updateOrder(objMap);

    }



}
