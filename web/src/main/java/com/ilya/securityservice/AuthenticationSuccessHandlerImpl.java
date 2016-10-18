package com.ilya.securityservice;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ilya on 25.09.2016.
 * success handler
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    ClientService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Client client = service.getByEmail(((User)authentication.getPrincipal()).getUsername());
        httpServletRequest.getSession().setAttribute("loggedClient",client);
        httpServletResponse.sendRedirect("/universe/getitems");
    }
}
