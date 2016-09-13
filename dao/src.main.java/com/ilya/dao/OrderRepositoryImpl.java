package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.model.Order;
import com.ilya.model.OrderForItem;
import com.ilya.utils.EntManUtl;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ilya.utils.EntManUtl.getEManager;

/**
 * Created by ilya on 21.08.2016.
 */
public class OrderRepositoryImpl implements OrderRepository {


    @Override
    public Map<Item,Integer> getItemsOfOrder(int orderId) {
        EntityManager entityManager = getEManager();
        Order or = entityManager.find(Order.class, orderId);
        List<OrderForItem> list = or.getOrderForItems();
        if (list != null) {
            Map<Item, Integer> map = new HashMap<>();
            for (OrderForItem orf : list) {
                map.put(orf.getItem(), orf.getQuantity());
            }
            EntManUtl.closeEManager();
            return map;
        }
        return null;
    }

    @Override
    public List<Order> getByClientId(int id) {
        EntityManager entityManager = getEManager();
        List<Order> list = entityManager.createQuery("SELECT o FROM Order o WHERE o.client.id =:clid",Order.class).setParameter("clid",id).getResultList();
        EntManUtl.closeEManager();
        if(entityManager.isOpen())return null;
        return list;
    }

    public  boolean addOrder(Order order) {
        EntityManager entityManager = getEManager();
        int a = 0;
        for (OrderForItem orderForItem : order.getOrderForItems()) {
            Item i = entityManager.find(Item.class, orderForItem.getItem().getId());
            entityManager.lock(i, LockModeType.OPTIMISTIC);
            if ((a = (i.getQuantity() - orderForItem.getQuantity())) < 0) {
//                entityManager.getTransaction().rollback();
                return false;
            }
            i.setQuantity(a);
        }
        entityManager.persist(order);
        return true;
    }

//    public boolean addOFI(OrderForItem orderForItem){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(orderForItem);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        return true;
//    }

    public static void main(String[] args) {
        List<Order> list = new OrderRepositoryImpl().getByClientId(100006);

    }
}
