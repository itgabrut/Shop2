package com.ilya.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ilya on 31.08.2016.
 */
@WebServlet(urlPatterns = "/fotoserver")
public class FotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("fotoId") != null) {
            HashMap<String, byte[]> map = (HashMap<String, byte[]>) req.getSession().getAttribute("Map");
            byte[] content = map == null ? null : map.get(req.getParameter("fotoId"));
            if (content != null) {
                map.remove(req.getParameter("fotoId"));
                resp.setContentType("image/jpg");
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            }
        }
    }
}
