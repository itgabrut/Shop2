package com.ilya.dao;

import com.ilya.model.Order;

import java.util.List;

/**
 * Created by ilya on 21.08.2016.
 */
public interface OrderRepository {

    boolean addOrder(Order order);

    List<Order> getByClientId(int id);
}
