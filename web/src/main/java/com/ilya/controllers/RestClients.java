package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by ilya on 30.09.2016.
 * rest
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


}
