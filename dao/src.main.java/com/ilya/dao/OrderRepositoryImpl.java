package com.ilya.dao;

import com.ilya.model.Client;
import com.ilya.model.Item;
import com.ilya.model.Order;
import com.ilya.model.OrderForItem;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 21.08.2016.
 */
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public Map<Item,Integer> getItemsOfOrder(int orderId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Order or = entityManager.find(Order.class, orderId);
        List<OrderForItem> list = or.getOrderForItems();
        if (list != null) {
            Map<Item, Integer> map = new HashMap<>();
            for (OrderForItem orf : list) {
                map.put(orf.getItem(), orf.getQuantity());
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return map;
        }
        return null;
    }

    @Override
    public List<Order> getByClientId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> list = entityManager.createQuery("SELECT o FROM Order o WHERE o.client.id =:clid",Order.class).setParameter("clid",id).getResultList();
        entityManager.close();
        if(entityManager.isOpen())return null;
        return list;
    }

    public  boolean addOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.close();
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
