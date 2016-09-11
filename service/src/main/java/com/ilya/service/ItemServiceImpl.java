package com.ilya.service;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.ItemRepositoryImpl;
import com.ilya.dao.OrderRepository;
import com.ilya.dao.OrderRepositoryImpl;
import com.ilya.model.Item;
import com.ilya.model.OrderForItem;
import com.ilya.utils.HibernateUtil;
import utils.BucketCheckerUtils;
import utils.FotoSaver;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 02.09.2016.
 */
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = new ItemRepositoryImpl();
    OrderRepository repository = new OrderRepositoryImpl();



    public Item getItem(int id) {
        return itemRepository.getItem(id);
    }

    public List<Item> getAll() {
        return null;
    }

    public boolean deleteItem(int id) {
        return false;
    }

    public boolean updateItem(Item item) {
        return false;
    }

    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public Map<Item, Integer> getItemsAndQuantityByOrder(int orderId) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Map<Item,Integer> map = repository.getItemsOfOrder(orderId);
        assert map!=null;
        return map;
    }

    public List<Item> getBucketItemsFromSession(HttpServletRequest req) {
        Map<String,Integer> map = (Map<String,Integer>)req.getSession().getAttribute("itemsMap");
        List<Item> list = new ArrayList<Item>();
        if(map!=null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Item item = itemRepository.getItem(Integer.parseInt(entry.getKey()));
                list.add(item);
                BucketCheckerUtils.transmittFotoToSessionMap(item.getFoto(),item.getId(), req.getSession());
            }
            return list;
        }
        return list;
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

    @Override
    public void redactItem(HttpServletRequest request)throws ServletException, IOException {
        Item item = new Item();
        item.setId(Integer.parseInt(request.getParameter("id")));
        item.setName(request.getParameter("name"));
        item.setDescription(request.getParameter("description"));
        item.setTheme(request.getParameter("theme"));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        item.setPrice(Integer.parseInt(request.getParameter("price")));
        InputStream inputStream = request.getPart("file").getInputStream();
        if(!item.getTheme().equals(request.getParameter("oldTheme")) || ! item.getName().equals(request.getParameter("oldName"))){
            FotoSaver.renameFotoDirectory(item.getName(),item.getTheme(),request.getParameter("oldName"),request.getParameter("oldTheme"));
        }
        if(inputStream.available()>0){
            byte[] buff = new byte[1024];
            int k=0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((k=inputStream.read(buff,0,1024))!=-1){
                baos.write(buff,0,k);
            }
            baos.flush();
            item.setFoto(baos.toByteArray());
            itemRepository.save(item);
        }
        else{
            itemRepository.saveWithoutFoto(item);
        }
    }
}
