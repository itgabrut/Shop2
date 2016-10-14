package com.ilya.controllers;

import com.ilya.model.Item;
import com.ilya.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.BucketCheckerUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 24.09.2016.
 */
@Controller
@SessionAttributes(value = {"Map","totalPrice","quantity","itemsMap"})
public class  Items {

    @Autowired
    private ItemService service;

    @ModelAttribute("Map")
    public Map<String,byte[]> getMap1(Model model){
        return model.containsAttribute("Map") ? (Map<String, byte[]>) model.asMap().get("Map") : new HashMap<String,byte[]>();
    }
    @ModelAttribute("itemsMap")
    public Map<String,Integer> getMap2(Model model){
        return model.containsAttribute("itemsMap") ? (Map<String,Integer>) model.asMap().get("itemsMap") : new HashMap<String,Integer>();
    }

    @RequestMapping(value = "/getitems",method = RequestMethod.GET)
    public String getItems(@RequestParam(value = "theme",required = false) String theme, Model model,@ModelAttribute("Map") Map<String,byte[]> map){
        List<String> themes = service.getThemes();
        model.addAttribute("themeList",themes);
        if(themes.size()!=0) {
            List<Item> list = service.getItemsByTheme(theme == null ? themes.get(0) : theme);
            BucketCheckerUtils.saveListFotosToMemory(map, list);
            model.addAttribute("itemList", list);
        }
        return "Items";
    }
    @RequestMapping(value = "/adminGetitems",method = RequestMethod.POST)
    public String postItems(
                             @RequestParam(value = "file",required = false) MultipartFile file,
                             @RequestParam(value = "id",required = false) Integer id,
                             @Valid Item item,
                             BindingResult result)throws IOException{
        if(id != null ){
            service.deleteItem(id);
            return "redirect:/getitems";
        }
        if(result.hasErrors()){
            return "Items";
        }
        else{
            byte[] foto = file.getBytes();
            item.setFoto(foto);
            item.setActive(true);
            service.addOrRedactItem(item);
            return "redirect:/getitems";
        }
    }

    @RequestMapping(value = "/checkout",method = RequestMethod.POST)
    public void addToBucket(@RequestParam(value = "totalPrice",required = false) String totalPrice,
                            @RequestParam(value = "quantityToAdd",required = false)String quantityToAdd,
                            @RequestParam(value = "itemId")String itemId,
                            @RequestParam(value = "itemPrice",required = false)String itemPrice,
                            Model model){
        if(totalPrice != null){
          BucketCheckerUtils.saveItemsAndPricesInSession(model,quantityToAdd,totalPrice,itemId);
        }
        else {
           BucketCheckerUtils.removeItemFromBucket(model,itemPrice,itemId);
        }
    }
    @RequestMapping(value ="/checkout",method = RequestMethod.PUT)
    public void clearBucket(Model model){
        model.addAttribute("totalPrice",0);
        model.addAttribute("quantity",0);
        model.addAttribute("itemsMap",new HashMap<String,Integer>());
    }
    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public String proceedCheckout(Model model){
        Map<String,Integer> map = (Map<String,Integer>)model.asMap().get("itemsMap");
        Map<String,byte[]> mapFoto = (Map<String,byte[]>)model.asMap().get("Map");
        List<Item> list =  service.getBucketItemsFromSession(map);
        BucketCheckerUtils.saveListFotosToMemory(mapFoto,list);
        model.addAttribute("itemsToCheckout",list);
        return "Checkout";
    }
    @RequestMapping(value = "/toClients")
    public String toClientsList(){
        return "Client_Ajax";
    }

    @RequestMapping("/databaseError1")
    String throwDatabaseException1() throws SQLException {
        throw new SQLException();
    }
}
