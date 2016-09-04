package com.ilya.service;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.ItemRepositoryImpl;
import com.ilya.model.Item;
import com.ilya.utils.BucketCheckerUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 02.09.2016.
 */
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = new ItemRepositoryImpl();

    public Item getItem(int id) {
        return null;
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
}
