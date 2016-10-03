package com.ilya.servlets;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import com.ilya.service.ItemServiceImpl;
import utils.BucketCheckerUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ilya on 10.09.2016.
 *
 * Process requests to show details of specific order
 */
//@WebServlet(urlPatterns = "/singleorder",name = "SingleOrder")
public class SingleOrderServlet extends HttpServlet {

    private ItemService service = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        assert req.getParameter("orderId") !=null;
        try {
            Map<Item,Integer> map = service.getItemsAndQuantityByOrder(Integer.parseInt(req.getParameter("orderId")));
//            BucketCheckerUtils.saveListFotosToMemory(req.getSession(),new ArrayList<>(map.keySet()));
            req.setAttribute("itemsForOrderMap",map);
            req.getRequestDispatcher("WEB-INF/jsp/Order.jsp").forward(req,resp);
        }
        catch (NumberFormatException d){
            resp.sendRedirect("getitems");
        }

    }
}
