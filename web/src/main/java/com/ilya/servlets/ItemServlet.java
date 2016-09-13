package com.ilya.servlets;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import com.ilya.service.ItemServiceImpl;
import utils.BucketCheckerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;


/**
 * Created by ilya on 31.08.2016.
 */
@WebServlet(urlPatterns = "/getitems")
@MultipartConfig
public class ItemServlet extends HttpServlet {

    ItemService service = new ItemServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<String> themes = service.getThemes();
            req.setAttribute("themeList",themes);
            if(themes.size()!=0) {
                List<Item> list = service.getItemsByTheme(req.getParameter("theme") == null ? themes.get(0) : req.getParameter("theme"));
                BucketCheckerUtils.saveListFotosToMemory(req.getSession(), list);
                req.setAttribute("itemList", list);
            }
            req.getRequestDispatcher("WEB-INF/jsp/Items.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameterMap().containsKey("id")) {
            service.deleteItem(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("getitems");
        }
        else{
            Part part = req.getPart("file");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream is = part.getInputStream();
            byte[] buff = new byte[1024];
            int k=0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((k=is.read(buff,0,1024))!=-1){
                baos.write(buff,0,k);
            }
            baos.flush();
            Item item = new Item();
            item.setName(req.getParameter("name"));
            item.setPrice(Integer.parseInt(req.getParameter("price")));
            item.setDescription(req.getParameter("description"));
            item.setTheme(req.getParameter("theme"));
            item.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            item.setFoto(baos.toByteArray());
            service.addOrRedactItem(item);
            resp.sendRedirect("/universe/getitems");
        }
    }
}
