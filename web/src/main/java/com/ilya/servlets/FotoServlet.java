package com.ilya.servlets;



import utils.FotoSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by ilya on 31.08.2016.
 *
 * Process requests for images
 */
@WebServlet(urlPatterns = "/fotoserver")
public class FotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("fotoId") != null) {
            HashMap<String, byte[]> map = (HashMap<String, byte[]>) req.getSession().getAttribute("Map");
            String id = req.getParameter("fotoId");
            byte[] content = null;
            if(map!=null)content = map.get(id);
            if (content != null) {
                map.remove(req.getParameter("fotoId"));
                resp.setContentType("image/jpg");
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            }
        }
        if(req.getParameter("path")!=null){
            OutputStream outputStream = resp.getOutputStream();
            Path path = Paths.get(req.getParameter("path"));
            Files.copy(path,outputStream);
            outputStream.flush();
        }
        if(req.getParameter("delete")!=null){
            FotoSaver.deleteFile(req.getParameter("delete"));
            resp.sendRedirect("single?id="+req.getParameter("itemId"));
        }

    }
}
