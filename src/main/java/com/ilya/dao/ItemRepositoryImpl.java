package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public class ItemRepositoryImpl implements ItemRepository {

    private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    public Item getItem(int itemId) {
        return null;
    }

    public boolean deleteItem(int itemId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Item item = entityManager.find(Item.class,itemId);
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
            return false;
        }
        entityManager.close();
        return true;
    }

    public List<Item> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select i from Item i",Item.class).getResultList();
    }

    public boolean save(Item item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        if(entityManager.isOpen())return false;
        return true;
    }
}
