package com.ilya.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ilya on 18.08.2016.
 */
public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        try {
//        HibernateUtil.getEntityManagerFactory();
            ClassLoader.getSystemClassLoader().loadClass("HibernateUtil");
        }
        catch (ClassNotFoundException d){}
}

    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.shutdown(); // Free all resources
    }
}
