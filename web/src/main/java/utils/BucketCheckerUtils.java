package utils;

import com.ilya.model.Item;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 03.09.2016.
 * Utils for work on bucket
 */
@SessionAttributes(value = {"Map","totalPrice","quantity","itemsMap"})
public final class BucketCheckerUtils {

    private BucketCheckerUtils(){}

    public static void saveItemsAndPricesInSession(HttpServletRequest req){
        int adder = req.getParameter("quantityToAdd") == null ? 1 : Integer.parseInt(req.getParameter("quantityToAdd"));
        HttpSession session = req.getSession();
        if(session.getAttribute("totalPrice")==null) session.setAttribute("totalPrice",Integer.parseInt(req.getParameter("totalPrice")));
        else session.setAttribute("totalPrice",(Integer)session.getAttribute("totalPrice")+Integer.parseInt(req.getParameter("totalPrice")));
        session.setAttribute("quantity",session.getAttribute("quantity") == null ? adder : (Integer)session.getAttribute("quantity")+adder);
        if (session.getAttribute("itemsMap")==null)session.setAttribute("itemsMap", new HashMap<String,Integer>());
        Integer quant = ((Map<String,Integer>)session.getAttribute("itemsMap")).get(req.getParameter("itemId"));
        ((Map<String,Integer>)session.getAttribute("itemsMap")).put(req.getParameter("itemId"),quant == null ? adder : quant+adder);
    }

    public static void saveItemsAndPricesInSession(Model model,String quantityToAdd,String totalPrice,String itemId){
        int adder = quantityToAdd == null ? 1 : Integer.parseInt(quantityToAdd);
        if(!model.containsAttribute("totalPrice")) model.addAttribute("totalPrice",Integer.parseInt(totalPrice));
        else model.addAttribute("totalPrice",(Integer)model.asMap().get("totalPrice")+Integer.parseInt(totalPrice));
        model.addAttribute("quantity",model.asMap().get("quantity") == null ? adder : (Integer)model.asMap().get("quantity")+adder);
        if (!model.containsAttribute("itemsMap"))model.addAttribute("itemsMap", new HashMap<String,Integer>());
        Integer quant = ((Map<String,Integer>)model.asMap().get("itemsMap")).get(itemId);
        ((Map<String,Integer>)model.asMap().get("itemsMap")).put(itemId,quant == null ? adder : quant+adder);
    }

    public static void clearBucket(HttpSession session){
        session.setAttribute("totalPrice",0);
        session.setAttribute("quantity",0);
        session.setAttribute("itemsMap",new HashMap<String,Integer>());
    }

    public static void transmitListFotoToSessionMap(List<Item> list,HttpSession session){
        HashMap<String, byte[]> map = (HashMap<String, byte[]>) session.getAttribute("Map");
        for(Item i : list){
//            transmittFotoToSessionMap(i.getFoto(),i.getId(),session);
            map.put(String.valueOf(i.getId()),i.getFoto());
        }
    }

    public static void removeItemFromBucket(HttpServletRequest req){
        String itemId = req.getParameter("itemId");
        String itemPrice = req.getParameter("itemPrice");
        Map<String,Integer> map = (Map<String, Integer>) req.getSession().getAttribute("itemsMap");
        int val = map.get(itemId);
        if(val==1)map.remove(itemId);
        else map.put(itemId,--val);
        HttpSession sess = req.getSession();
        Integer quantity = (Integer)sess.getAttribute("quantity");
        quantity--;
        sess.setAttribute("quantity",quantity);
        Integer totalPrice = (Integer)sess.getAttribute("totalPrice");
        totalPrice = totalPrice - Integer.parseInt(itemPrice);
        sess.setAttribute("totalPrice",totalPrice);
    }

    public static void removeItemFromBucket(Model model,String itemPrice,String itemId){
        Map<String,Integer> map = (Map<String, Integer>) model.asMap().get("itemsMap");
        int val = map.get(itemId);
        if(val==1)map.remove(itemId);
        else map.put(itemId,--val);
        Integer quantity = (Integer) model.asMap().get("quantity");
        quantity--;
        model.addAttribute("quantity",quantity);
        Integer totalPricenew = (Integer)model.asMap().get("totalPrice");
        totalPricenew = totalPricenew - Integer.parseInt(itemPrice);
        model.addAttribute("totalPrice",totalPricenew);
    }

    public static void saveFotoToMemory(Map<String,byte[]> map,Item item){
        map.put(String.valueOf(item.getId()),item.getFoto());
    }

    public static void saveListFotosToMemory(Map<String,byte[]> map, List<Item> list){
//        Map<String, byte[]> map = new HashMap<>();
        for (Item i : list) {
            map.put(String.valueOf(i.getId()), i.getFoto());
        }
//        model.addAttribute("Map", map);
    }
}
