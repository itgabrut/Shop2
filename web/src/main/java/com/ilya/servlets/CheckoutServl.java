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
import java.util.List;
import java.util.Map;


/**
 * Created by ilya on 03.09.2016.
 *
 *   Forwards to Checkout.jsp adding List Checked Items from client card
 */
//@WebServlet(urlPatterns = "/checkout")
public class CheckoutServl extends HttpServlet {

    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Integer> map = (Map<String,Integer>)req.getSession().getAttribute("itemsMap");
        List<Item> list =  itemService.getBucketItemsFromSession(map);
        BucketCheckerUtils.transmitListFotoToSessionMap(list,req.getSession());
        req.setAttribute("itemsToCheckout",list);
        req.getRequestDispatcher("WEB-INF/jsp/Checkout.jsp").forward(req,resp);
    }
         /**
          * Method process request from Items.jsp with parameters totalPrice and itemId (Add item to bucket) or
          * from Checkout.jsp with parameter itemId (Delete item from bucket)
          * **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("totalPrice")!=null) BucketCheckerUtils.saveItemsAndPricesInSession(req);
        else{
            BucketCheckerUtils.removeItemFromBucket(req);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BucketCheckerUtils.clearBucket(req);
    }
}
