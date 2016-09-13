package com.ilya.servlets;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import com.ilya.service.ItemServiceImpl;
import utils.BucketCheckerUtils;
import utils.FotoSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by ilya on 04.09.2016.
 *
 * Process requests to show single Item information.
 * Process requests for Add or Redact single Item
 */
@WebServlet(urlPatterns = "/single")
@MultipartConfig
public class Single  extends HttpServlet {

   private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Item item = itemService.getItem(Integer.parseInt(req.getParameter("id")));
//        assert item != null;
            BucketCheckerUtils.saveFotoToMemory(req.getSession(), item);
            req.setAttribute("item", item);
            List<Path> fotos = FotoSaver.getPathsOfFotos(item);
            req.setAttribute("fotosList", fotos);
            req.getRequestDispatcher("WEB-INF/jsp/Single.jsp").forward(req, resp);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            resp.sendRedirect("getitems");
        }
    }

   /** Save redacted item to database
    * or adds/replaces standarted images to FileSystem
    * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPart("file1")!=null || req.getPart("file2")!=null || req.getPart("file3")!=null){
            saveFotoToFileSystem(req);
            resp.sendRedirect("single?id="+req.getParameter("id"));
        }
       else if(req.getPart("file")!=null){
            Item item = new Item();
            item.setId(Integer.parseInt(req.getParameter("id")));
            item.setName(req.getParameter("name"));
            item.setDescription(req.getParameter("description"));
            item.setTheme(req.getParameter("theme"));
            item.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            item.setPrice(Integer.parseInt(req.getParameter("price")));
            InputStream inputStream = req.getPart("file").getInputStream();
            if (!item.getTheme().equals(req.getParameter("oldTheme")) || !item.getName().equals(req.getParameter("oldName"))) {
                FotoSaver.renameFotoDirectory(item.getName(), item.getTheme(), req.getParameter("oldName"), req.getParameter("oldTheme"));
            }
            if(inputStream.available() > 0){
                byte[] buff = new byte[1024];
                int k ;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((k = inputStream.read(buff, 0, 1024)) != -1) {
                    baos.write(buff, 0, k);
                }
                baos.flush();
                item.setFoto(baos.toByteArray());
            }
            itemService.addOrRedactItem(item);
            resp.sendRedirect("single?id="+req.getParameter("id"));
        }
    }

    public void saveFotoToFileSystem(HttpServletRequest req)throws ServletException, IOException {
        if(req.getPart("file1")!=null){
            FotoSaver.saveFotoToFileSystem(req.getPart("file1").getInputStream(),req.getParameter("theme"),req.getParameter("itemName"),"1");
        }
        if(req.getPart("file2")!=null){
            FotoSaver.saveFotoToFileSystem(req.getPart("file2").getInputStream(),req.getParameter("theme"),req.getParameter("itemName"),"2");
        }
        if(req.getPart("file3")!=null){
            FotoSaver.saveFotoToFileSystem(req.getPart("file3").getInputStream(),req.getParameter("theme"),req.getParameter("itemName"),"3");
        }
    }
}
