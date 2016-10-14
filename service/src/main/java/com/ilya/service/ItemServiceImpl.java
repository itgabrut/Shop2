package com.ilya.service;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.OrderRepository;
import com.ilya.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.context.Theme;
import utils.FotoSaver;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 02.09.2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository ;

    private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

//    static {
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);
//
//    }

    /**
     *
     * @param id Id of Item entity
     * @return   Item object
     */
    public Item getItem(int id) {
        LOG.info("Item {} asks get method",id);
        return itemRepository.getItem(id);
    }

    public List<Item> getAll() {
        return null;
    }

    /**
     * Removes Item entity from db using it Id
     * @param id Id
     * @return true on success
     */
    @Transactional
    public boolean deleteItem(int id) {
        Item inactive = itemRepository.deleteSoft(id);
        LOG.info("Item {} delete Soft success",id);
        try {
            FotoSaver.deleteByItem(inactive.getName(),inactive.getTheme());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addItem(Item item) {
        return false;
    }

    /**
     * It provides information about ordering
     * @param orderId  Id of Order entity
     * @return          Map, represents Item entity as key, and quantity as value
     */
    @Override
    public Map<Item, Integer> getItemsAndQuantityByOrder(int orderId) {
        Map<Item,Integer> map = orderRepository.getItemsOfOrder(orderId);
        assert map!=null;
        LOG.info("Items counted for order id = {}",orderId);
        return map;
    }

    /**
     *  List of Items from db
     * @param map Map containing Id's of client's card
     * @return List of Item entities
     */
    public List<Item> getBucketItemsFromSession(Map<String,Integer> map) {
//        Map<String,Integer> map = (Map<String,Integer>)req.getSession().getAttribute("itemsMap");
        List<Item> list = new ArrayList<>();
        if(map!=null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Item item = itemRepository.getItem(Integer.parseInt(entry.getKey()));
                list.add(item);
            }
            LOG.info("Items from Client card");
            return list;
        }
        return list;
    }

    /**
     * Returns List Item entities by categories (theme)
     * @param s String, Item theme
     * @return  Returns List Item entities
     */
    @Override
    public List<Item> getItemsByTheme(String s) {
        List<Item> list = itemRepository.getItemsByTheme(s);
        return list;
    }

    /**
     *
     * @return List of String objects from db, representing existing Item categories
     */
    @Override
    public List<String> getThemes() {
        List<String> list = itemRepository.getThemes();
        return list;
    }

    /**
     * Edits Item if no Id present or adds it to db
     * @param item Item entity
     */
    @Override
    @Transactional
    public void addOrRedactItem(Item item) {
        itemRepository.save(item);
        LOG.info("Item {} successfully saved",item.getId());
    }

}
