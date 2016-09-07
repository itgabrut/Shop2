package com.ilya.dao;

import com.ilya.model.Order;

/**
 * Created by ilya on 21.08.2016.
 */
public interface OrderRepository {

    boolean addOrder(Order order);
}
