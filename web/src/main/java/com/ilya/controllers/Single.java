package com.ilya.controllers;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.BucketCheckerUtils;
import utils.FotoSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 25.09.2016.
 */
@Controller
@SessionAttributes("Map")
public class Single {

    @Autowired
    private ItemService itemService;

    @ModelAttribute("Map")
    public Map<String,byte[]> getMap(Model model){
        return model.containsAttribute("Map") ? (Map<String, byte[]>) model.asMap().get("Map") : new HashMap<String,byte[]>();
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String getSingle(@RequestParam("id") String id, Model model,@ModelAttribute("Map") Map<String,byte[]> map){
        try {
            Item item = itemService.getItem(Integer.parseInt(id));
//        assert item != null;
            BucketCheckerUtils.saveFotoToMemory(map, item);
            model.addAttribute("item", item);
            List<Path> fotos = FotoSaver.getPathsOfFotos(item);
            model.addAttribute("fotosList", fotos);
            return "Single";
        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:getitems";
        }
    }

    @RequestMapping(value = "/single/files",method = RequestMethod.POST)
    public String saveFotos(@RequestParam(value = "files",required = false) List<MultipartFile> list,
                            @RequestParam("theme")String theme,
                            @RequestParam("itemName") String itemName,
                            @RequestParam("id") String id){
        if(list!=null){
            try {
                for (MultipartFile file : list) {
                    FotoSaver.saveFotoToFileSystem(file.getInputStream(), theme, itemName, String.valueOf(list.lastIndexOf(file)+1));
                }
            }
            catch (IOException e){
                e.printStackTrace();
                return "redirect:single" + id;
            }
        }
        return "redirect:single?id=" + id;
    }

    @RequestMapping(value = "/single/update",method = RequestMethod.POST)
    public String redactItem(@ModelAttribute Item item,
                             @RequestParam("oldTheme")String oldTheme,
                             @RequestParam("oldName")String oldName,
                             @RequestParam("id")String id,
                             @RequestParam(value = "file",required = false) MultipartFile file){
        try {
            if (!item.getTheme().equals(oldTheme) || !item.getName().equals(oldName)) {
                FotoSaver.renameFotoDirectory(item.getName(), item.getTheme(), oldName, oldTheme);
            }
            if(file != null){
                item.setFoto(file.getBytes());
            }
            item.setProxyId(Integer.valueOf(id));
            itemService.addOrRedactItem(item);
        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:getitems";
        }
        return "redirect:single?id=" + id;

    }
}
