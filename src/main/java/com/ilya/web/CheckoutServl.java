package com.ilya.web;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import com.ilya.service.ItemServiceImpl;
import com.ilya.utils.BucketCheckerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 * Created by ilya on 03.09.2016.
 */
@WebServlet(urlPatterns = "/checkout")
public class CheckoutServl extends HttpServlet {

    ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          List<Item> list =  itemService.getBucketItemsFromSession(req);
          req.setAttribute("itemsToCheckout",list);
        req.getRequestDispatcher("/Checkout.jsp").forward(req,resp);
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
