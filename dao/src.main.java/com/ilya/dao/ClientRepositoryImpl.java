package com.ilya.dao;



import com.ilya.model.Client;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


/**
 * Created by ilya on 18.08.2016.
 */

public class ClientRepositoryImpl implements ClientRepository {


   private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
//            HibernateUtil.getEntityManagerFactory().createEntityManager();

    public Client getClient(int clientId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Client.class,clientId);
    }
    public boolean deleteClient(int clientId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Client client = entityManager.find(Client.class,clientId);
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
            return false;
        }
        entityManager.close();
        return true;
    }

    public boolean save(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if(client.getId()==0){
            entityManager.persist(client);
        }
        else entityManager.merge(client);
        entityManager.getTransaction().commit();
       if (entityManager.getTransaction().isActive()){
           entityManager.getTransaction().rollback();
           return false;
       }
        return true;
    }

    public List<Client> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNamedQuery("Client.getAll",Client.class).getResultList();
    }
}
