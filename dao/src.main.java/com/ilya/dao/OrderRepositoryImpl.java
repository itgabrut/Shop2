package com.ilya.dao;

import com.ilya.model.Client;
import com.ilya.model.Item;
import com.ilya.model.Order;
import com.ilya.model.OrderForItem;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 21.08.2016.
 */
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

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
