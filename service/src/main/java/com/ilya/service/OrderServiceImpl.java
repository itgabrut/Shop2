package com.ilya.service;

import com.ilya.dao.OrderRepository;
import com.ilya.dao.OrderRepositoryImpl;
import com.ilya.model.Client;
import com.ilya.model.Item;
import com.ilya.model.Order;
import com.ilya.model.OrderForItem;
import com.ilya.utils.EntManUtl;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ilya.utils.EntManUtl.getEManager;

/**
 * Created by ilya on 09.09.2016.
 */
public class OrderServiceImpl implements OrderService {

    OrderRepository repository = new OrderRepositoryImpl();

//    public boolean addOrder(Order order){
//        return false;
//
//    }

    @Override
    public List<Order> getOrdersByClientId(int id) {
       List<Order> list = repository.getByClientId(id);
        return list == null ? null : list;
    }

    @Override
    public boolean addOrder(Map<Integer, Integer> m,Client current) {
        Order order = Order.getSimpleOrder();
        List<OrderForItem> list = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entr : m.entrySet()){
            Item item = new Item();
            item.setId(entr.getKey());
            OrderForItem orderForItem = new OrderForItem();
            orderForItem.setQuantity(entr.getValue());
            orderForItem.setItem(item);
            orderForItem.setOrder(order);
            list.add(orderForItem);
        }
        order.setOrderForItems(list);
        order.setClient(current);
        EntManUtl.startTransaction();
        try {
           if(repository.addOrder(order)){
               EntManUtl.commitTransaction();
           }
            else EntManUtl.rollback();
        }
        catch (OptimisticLockException exc){
            exc.printStackTrace();
            EntManUtl.rollback();
            return false;
        }
        finally {
            EntManUtl.closeEManager();
        }
        return true;
    }
}
