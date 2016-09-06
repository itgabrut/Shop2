package com.ilya.web;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import com.ilya.service.ItemServiceImpl;
import com.ilya.utils.FotoSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by ilya on 04.09.2016.
 */
@WebServlet(urlPatterns = "/single")
@MultipartConfig
public class Single  extends HttpServlet {

    ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item item = itemService.getItem(Integer.parseInt(req.getParameter("id")));
        FotoSaver.saveFotoToMemory(req.getSession(),item);
        req.setAttribute("item",item);
        List<Path> fotos = FotoSaver.getPathsOfFotos(item);
        req.setAttribute("fotosList",fotos);
        req.getRequestDispatcher("/Single.jsp").forward(req,resp);
    }

   /** Save redacted item to database
    * or adds/replaces standarted images to FileSystem
    * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPart("file1")!=null || req.getPart("file2")!=null || req.getPart("file3")!=null){
            itemService.saveFotoToFileSystem(req);
            resp.sendRedirect("single?id="+req.getParameter("id"));
        }
       else if(req.getPart("file")!=null){
            itemService.redactItem(req);
            resp.sendRedirect("single?id="+req.getParameter("id"));
        }
    }
}
