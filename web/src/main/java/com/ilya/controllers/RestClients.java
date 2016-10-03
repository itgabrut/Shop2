package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ilya on 30.09.2016.
 */
@RestController
@RequestMapping(value = "/rest/admin")
@SessionAttributes(value = "loggedClient")
public class RestClients {

    @Autowired
    ClientService service;

    @RequestMapping(value = "/get",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients(){
        return service.getAll();
    }

    @RequestMapping(value = "/getOne",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Client getOne(@RequestParam(value = "id")String id){
        return service.getClient(Integer.parseInt(id));
    }

    @RequestMapping(value = "/redact",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void redact(@RequestBody Client client){
            if(client != null){
                service.addClient(client);
            }
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public void del(@RequestParam(value = "id") String id){
        service.deleteClient(Integer.parseInt(id));
    }

    @RequestMapping(value = "/pass",method = RequestMethod.POST)
    public void changepass(@RequestParam(value = "password")String password,
                           @RequestParam(value = "pass1")String passnew1,
                           @RequestParam(value = "pass2")String passnew2,
                           HttpServletResponse response,
                           Model model)throws IOException{
          if(!passnew1.equals(passnew2)) {
              response.sendError(HttpServletResponse.SC_CONFLICT, "Password match");
              return;
          }
        Client loggedClient = (Client)model.asMap().get("loggedClient");
        if(!service.checkPasswOnChange(loggedClient, password))response.sendError(HttpServletResponse.SC_CONFLICT,"Wrong password");
        else {
               loggedClient.setPassword(passnew1);
               service.changePassOrMerge(loggedClient);
        }
    }




}
