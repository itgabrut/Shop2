package utils;

import com.ilya.model.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 03.09.2016.
 */
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

    public static void clearBucket(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("totalPrice",0);
        session.setAttribute("quantity",0);
        session.setAttribute("itemsMap",new HashMap<String,Integer>());
    }

    public static void transmittFotoToSessionMap(byte[] b,int id,HttpSession session){
        HashMap<String, byte[]> map = (HashMap<String, byte[]>) session.getAttribute("Map");
        map.put(String.valueOf(id),b);
    }

    public static void transmitListFotoToSessionMap(List<Item> list,HttpSession session){
        for(Item i : list){
            transmittFotoToSessionMap(i.getFoto(),i.getId(),session);
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

    public static void saveFotoToMemory(HttpSession session,Item item){
        Map<String,byte[]> fotoMap = (Map<String,byte[]>)session.getAttribute("Map");
        fotoMap.put(String.valueOf(item.getId()),item.getFoto());
    }

    public static void saveListFotosToMemory(HttpSession session,List<Item> list){
        Map<String, byte[]> map = new HashMap<>();
        for (Item i : list) {
            map.put(String.valueOf(i.getId()), i.getFoto());
        }
        session.setAttribute("Map", map);
    }
}
