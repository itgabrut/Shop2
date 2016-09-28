package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.validators.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ilya on 26.09.2016.
 */
@Controller
public class Authentication  {

    @Autowired
    ClientValidator validator;
    @Autowired
    ClientService service;

    @ModelAttribute("registration")
    public Client getReg(Model model){
        return model.containsAttribute("registration") ? (Client) model.asMap().get("registration") : new Client();
    }

    @RequestMapping( value = "/login")
    public ModelAndView jspResolver(@RequestParam(value = "logout", required = false) String l,
                                    @RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView = new ModelAndView("Register");
        if(error!=null) return modelAndView.addObject("error","Email or password doesn't match");
        else if(l != null) return modelAndView.addObject("logout","LOGGED OUT SUCCESSFULLY");
        else return modelAndView;
    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public String registration(@ModelAttribute("registration")Client client, BindingResult result){
        validator.validate(client,result);
        if(result.hasErrors()){
            return "Register";
        }
        service.addClient(client);
        return "Items";
    }
}
