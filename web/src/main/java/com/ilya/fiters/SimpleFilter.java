package com.ilya.fiters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ilya on 08.09.2016.
 */
public class SimpleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          HttpServletResponse hresponse = (HttpServletResponse)response;
        HttpServletRequest hreq = (HttpServletRequest) request;
        String asked =  hreq.getRequestURI();
        HttpSession session = hreq.getSession(false);
        if(session!=null && session.getAttribute("loggedClient")!=null && asked.equals("/universe/Orders.jsp")){
            hreq.getRequestDispatcher("/WEB-INF/jsp/Orders.jsp").forward(hreq,hresponse);
        }
        else if(asked.equals("/universe/Register.jsp")){
            hreq.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(hreq,hresponse);
        }
        else
        hresponse.sendRedirect("getitems");
    }

    @Override
    public void destroy() {

    }
}
