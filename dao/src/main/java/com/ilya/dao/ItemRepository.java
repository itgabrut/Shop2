package com.ilya.dao;


import com.ilya.model.Item;

import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public interface ItemRepository {

    Item getItem(int itemId);

    void deleteItem(int itemId);

    List<Item> getAll();

     void save(Item item);

     List<String> getThemes();

     List<Item> getItemsByTheme(String theme);
}
