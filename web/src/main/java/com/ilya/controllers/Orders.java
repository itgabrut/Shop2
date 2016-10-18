package com.ilya.controllers;


import com.ilya.model.Client;
import com.ilya.model.Item;
import com.ilya.model.enums_utils.Role;
import com.ilya.service.ClientService;
import com.ilya.service.ItemService;
import com.ilya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.BucketCheckerUtils;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ilya on 27.09.2016.
 * order controller
 */
@Controller
@SessionAttributes(value = {"loggedClient","Map","itemsMap"})
public class Orders {

    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/orders")
    public String toOrderView(Model model){
        Client client =(Client) model.asMap().get("loggedClient");
        if(client.getRoles().contains(Role.ROLE_ADMIN))return "AllOrders";
        else return "Orders";
    }

    @RequestMapping(value = "/adminOrders")
    public String toAdminOrderView(@RequestParam(value = "clientId")String clientId,Model model){
        model.addAttribute("clientId",clientId);
        return "OrdersForAdmin";
    }

    @RequestMapping(value = "/orderstopost",method = RequestMethod.POST)
    public String postOrder(Model model, HttpSession sess,HttpServletResponse resp)throws IOException{
        Map<String,Integer> map = (Map) model.asMap().get("itemsMap");
            int clientId =((Client)model.asMap().get("loggedClient")).getId();
            Client asked = clientService.getClient(clientId);
            if (asked == null || !orderService.addOrder(map, asked)) resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            else BucketCheckerUtils.clearBucket(sess);
        return "redirect:/orders";
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
        else return "redirect:/getitems";
    }
    @RequestMapping(value = "/adminSingleorder",method = RequestMethod.GET)
    public String adminSingleOrder(@RequestParam("orderId")String orderId,
                                   Model model){
            Map<Item,Integer> map = itemService.getItemsAndQuantityByOrder(Integer.parseInt(orderId));
            BucketCheckerUtils.saveListFotosToMemory((Map<String,byte[]>)model.asMap().get("Map"),new ArrayList<>(map.keySet()));
            model.addAttribute("itemsForOrderMap",map);
            return "Order";
    }
}
