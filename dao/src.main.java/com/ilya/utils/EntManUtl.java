package com.ilya.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by ilya on 13.09.2016.
 */
public final class EntManUtl {

    private EntManUtl(){}

    private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    private static ThreadLocal<EntityManager> managerThreadLocal = new ThreadLocal<>();



    public static EntityManager getEManager(){
        if(managerThreadLocal.get() == null){
            managerThreadLocal.set(entityManagerFactory.createEntityManager());
        }
        return managerThreadLocal.get();
    }

    public static void startTransaction(){
        getEManager().getTransaction().begin();
    }

    public static void commitTransaction(){
        getEManager().getTransaction().commit();
    }

    public static void rollback(){
        if(getEManager().getTransaction().isActive())getEManager().getTransaction().rollback();
    }

    public static void closeEManager(){
        getEManager().close();
        managerThreadLocal.remove();
    }

}
