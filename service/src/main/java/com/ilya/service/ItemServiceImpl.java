package com.ilya.service;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.ItemRepositoryImpl;
import com.ilya.dao.OrderRepository;
import com.ilya.dao.OrderRepositoryImpl;
import com.ilya.model.Item;
import com.ilya.utils.EntManUtl;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 02.09.2016.
 */
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = new ItemRepositoryImpl();
    OrderRepository orderRepository = new OrderRepositoryImpl();



    public Item getItem(int id) {
        return itemRepository.getItem(id);
    }

    public List<Item> getAll() {
        return null;
    }

    public boolean deleteItem(int id) {
        try {
            EntManUtl.startTransaction();
            itemRepository.deleteItem(id);
            EntManUtl.commitTransaction();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            EntManUtl.rollback();
            return false;
        }
        finally {
            EntManUtl.closeEManager();
        }
    }

    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public Map<Item, Integer> getItemsAndQuantityByOrder(int orderId) {
        Map<Item,Integer> map = orderRepository.getItemsOfOrder(orderId);
        assert map!=null;
        return map;
    }

    public List<Item> getBucketItemsFromSession(Map<String,Integer> map) {
//        Map<String,Integer> map = (Map<String,Integer>)req.getSession().getAttribute("itemsMap");
        List<Item> list = new ArrayList<Item>();
        if(map!=null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Item item = itemRepository.getItem(Integer.parseInt(entry.getKey()));
                list.add(item);
            }
            return list;
        }
        return list;
    }

    @Override
    public List<Item> getItemsByTheme(String s) {
        List<Item> list = itemRepository.getItemsByTheme(s);
        EntManUtl.closeEManager();
        return list;
    }

    @Override
    public List<String> getThemes() {
        List<String> list = itemRepository.getThemes();
        EntManUtl.closeEManager();
        return list;
    }

    @Override
    public void addOrRedactItem(Item item)throws ServletException, IOException {
        try {
            EntManUtl.startTransaction();
            if (item.getFoto()!=null) {
                itemRepository.save(item);
                EntManUtl.commitTransaction();
            } else {
                itemRepository.saveWithoutFoto(item);
                EntManUtl.commitTransaction();
            }
        }
        catch (PersistenceException e){
            e.printStackTrace();
            EntManUtl.rollback();
        }
        finally {
            EntManUtl.closeEManager();
        }
    }
}
