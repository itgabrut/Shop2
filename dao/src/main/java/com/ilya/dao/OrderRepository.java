package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 21.08.2016.
 * order repo
 */
public interface OrderRepository {

    boolean addOrder(Order order);

    Order updateOrder(Order order);

    List<Order> getByClientId(int id);

    Map<Item,Integer> getItemsOfOrder(int orderId);

    Order getById(int id);

    List<Order> getAll();

    List<Order> getBetween(String from,String to);

    List<Order> getLazyList(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters);

    long count();
}
