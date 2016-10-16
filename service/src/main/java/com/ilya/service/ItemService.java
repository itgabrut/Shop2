package com.ilya.service;

import com.ilya.model.Item;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by ilya on 02.09.2016.
 */
public interface ItemService {

    Item getItem(int id);

    List<Item> getAll();

    boolean deleteItem(int id);

    boolean addItem(Item item);

    Map<Item,Integer> getItemsAndQuantityByOrder(int orderId);

    List<Item> getBucketItemsFromSession(Map<String,Integer> map);

    void addOrRedactItem(Item item);

    List<String> getThemes(Locale locale);

    List<Item> getItemsByTheme(String s,Locale locale);
}
