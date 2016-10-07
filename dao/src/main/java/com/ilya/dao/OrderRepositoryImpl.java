package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.model.Order;
import com.ilya.model.OrderForItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 21.08.2016.
 */
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<Item,Integer> getItemsOfOrder(int orderId) {
        Order or = entityManager.find(Order.class, orderId);
        List<OrderForItem> list = or.getOrderForItems();
        if (list != null) {
            Map<Item, Integer> map = new HashMap<>();
            for (OrderForItem orf : list) {
                map.put(orf.getItem(), orf.getQuantity());
            }
            return map;
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("select o from Order o order by o.date desc").getResultList();
    }

    @Override
    public Order getById(int id) {
        return entityManager.find(Order.class,id);
    }

    @Override
    public List<Order> getByClientId(int id) {
        List<Order> list = entityManager.createQuery("SELECT o FROM Order o WHERE o.client.id =:clid",Order.class).setParameter("clid",id).getResultList();
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public  boolean addOrder(Order order) {
        int a;
        for (OrderForItem orderForItem : order.getOrderForItems()) {
//            entityManager.lock(i, LockModeType.OPTIMISTIC);
            Item i = orderForItem.getItem();
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

}
