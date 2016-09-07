package com.ilya.dao;

import com.ilya.model.Order;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by ilya on 21.08.2016.
 */
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    public boolean addOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
}
