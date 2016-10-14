package com.ilya.controllers;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import com.ilya.validators.ClientEditValidator;
import com.ilya.validators.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ilya on 26.09.2016.
 */
@Controller
@SessionAttributes(value = "loggedClient")
public class Authentication  {

    @Autowired
    ClientValidator validator;
    @Autowired
    ClientEditValidator editValidator;
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
        return "redirect:/getitems";
    }

    @RequestMapping(value = "/changeDetails",method = RequestMethod.POST)
    public String changeDetails(@ModelAttribute("loggedClient") Client client, BindingResult result){
        editValidator.validate(client,result);
        if(result.hasErrors()){
            return "ClientDetails";
        }
        service.addClient(client);
        return "redirect:/toDetails";
    }

    @RequestMapping(value = "/toDetails")
    public String toDetails()
    {
        return "ClientDetails";
    }


    @RequestMapping(value = "/pass",method = RequestMethod.POST)
    public void changepass(@RequestParam(value = "password")String password,
                           @RequestParam(value = "pass1")String passnew1,
                           @RequestParam(value = "pass2")String passnew2,
                           HttpServletResponse response,
                           Model model)throws IOException {
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
