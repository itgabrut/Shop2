package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 21.08.2016.
 */
public interface OrderRepository {

    boolean addOrder(Order order);

    List<Order> getByClientId(int id);

    Map<Item,Integer> getItemsOfOrder(int orderId);

    Order getById(int id);

    List<Order> getAll();
}
