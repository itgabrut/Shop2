package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.model.Order;
import com.ilya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

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

}
