package com.ilya.service;

import com.ilya.model.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilya on 02.09.2016.
 */
public interface ItemService {

    Item getItem(int id);

    List<Item> getAll();

    boolean deleteItem(int id);

    boolean updateItem(Item item);

    boolean addItem(Item item);

    List<Item> getBucketItemsFromSession(HttpServletRequest req);
}
