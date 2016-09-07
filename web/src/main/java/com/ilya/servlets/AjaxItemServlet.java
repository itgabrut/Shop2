package com.ilya.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilya.dao.ItemRepository;
import com.ilya.dao.ItemRepositoryImpl;
import com.ilya.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 28.08.2016.
 */
@WebServlet(urlPatterns = "/ajax/items")
@MultipartConfig
public class AjaxItemServlet extends HttpServlet {

    ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("fotoId")!=null){
            HashMap<String,byte[]> map = (HashMap<String, byte[]>) req.getSession().getAttribute("Map");
            byte[] content = map.get(req.getParameter("fotoId"));
            map.remove(req.getParameter("fotoId"));
            if(content!=null) {
                resp.setContentType("image/jpg");
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            }
        }
        else {

            ObjectMapper objectMapper = new ObjectMapper();
            List<Item> list = itemRepository.getAll();
            Map<String,byte[]> map = new HashMap<String, byte[]>();
            for(Item i : list){
                map.put(String.valueOf(i.getId()),i.getFoto());
            }
            req.getSession().setAttribute("Map",map);
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), list);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemRepository.deleteItem(Integer.parseInt(req.getParameter("id")));
    }
}
