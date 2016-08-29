package com.ilya.dao;


import com.ilya.model.Item;

import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public interface ItemRepository {

    Item getItem(int itemId);

    boolean deleteItem(int itemId);

    List<Item> getAll();

    boolean save(Item item);
}
