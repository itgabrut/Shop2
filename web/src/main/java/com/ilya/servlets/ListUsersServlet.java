package com.ilya.servlets;


import com.ilya.dao.ItemRepository;
import com.ilya.dao.ItemRepositoryImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ilya on 28.08.2016.
 */
@WebServlet(urlPatterns = "/help",name = "ListUsers")
public class ListUsersServlet extends HttpServlet {

    ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/Client_Ajax.jsp").forward(req,resp);
    }

}
