package com.ilya.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.FotoSaver;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilya on 25.09.2016.
 * Image loader
 */
@Controller
@RequestMapping("/fotoserver")
public class Foto {

    @RequestMapping(value = "/db")
    @ResponseBody
    public byte[] getFotoFromDb(@RequestParam("fotoId") String fotoId, HttpSession session){
        Map<String,byte[]> map = (HashMap)session.getAttribute("Map");
        byte[] foto =  map.get(fotoId);
        map.remove(fotoId);
        return foto;
    }

    @RequestMapping("/sys")
    @ResponseBody
    public byte[] getFotoFromSys(@RequestParam("path") String strpath) throws IOException{
        Path path = Paths.get(strpath);
        return   Files.readAllBytes(path);
    }
    @RequestMapping("/del")
    public String delete(@RequestParam("delete") String delete,@RequestParam("itemId") String itemId)throws IOException{
        FotoSaver.deleteFile(delete);
        return "redirect:/single?id="+itemId;
    }
}
