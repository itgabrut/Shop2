package com.ilya.utils;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ilya on 18.08.2016.
 */
public class HibernateUtil {

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
}
