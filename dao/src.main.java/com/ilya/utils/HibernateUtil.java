package com.ilya.utils;


import com.ilya.model.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ilya on 18.08.2016.
 */
public final class HibernateUtil {

    private HibernateUtil(){}

    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("persist.unit");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    public static void shutdown(){
        entityManagerFactory.close();
    }

    public static void main(String[] args) throws Exception{
        Item item = new Item();
        item.setName("alalala2");
        Path p = Paths.get("C:\\temp\\k.jpg");
        item.setFoto(Files.readAllBytes(p));
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }
}
