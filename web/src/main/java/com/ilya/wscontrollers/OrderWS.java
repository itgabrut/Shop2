package com.ilya.wscontrollers;

import com.ilya.dtoForWS.DtoServiceOrder;
import com.ilya.dtoForWS.TOrder;
import com.ilya.model.Client;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 05.10.2016.
 * web services controller
 */
@RestController
@RequestMapping(value = "/ws")
@SessionAttributes(value = "loggedClient")
public class OrderWS {

    @Autowired
   private DtoServiceOrder dtoServiceOrder;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public void checkIsAdmin(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             Model model){
        Client client = clientService.getByEmail(username);
        if(clientService.checkPasswOnChange(client,password)){
            model.addAttribute("loggedClient",client);
        }
    }


    @RequestMapping(value = "/lazy",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TOrder> getLazy(@RequestParam Map<String, String> params,Model model){
        Client client = (Client)model.asMap().get("loggedClient");


        if(client.getEmail().equals(params.get("username")))
        return dtoServiceOrder.getLazyList(params);
        else throw new org.springframework.security.access.AccessDeniedException("403 returned");
    }

    @RequestMapping(value = "/between",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TOrder> getBetween(@RequestParam("from") String from,
                                   @RequestParam("to") String to){
            return dtoServiceOrder.getBetween(from,to);
    }

}
