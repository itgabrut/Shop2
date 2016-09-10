package com.ilya.dao;



import com.ilya.model.Client;
import com.ilya.utils.HibernateUtil;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.UniqueConstraint;
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

    @Override
    public Client getByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       Client cl = (Client) entityManager.createQuery("select u from Client u where u.email =:email").setParameter("email",email).getSingleResult();
       entityManager.close();
        return cl;
    }

    public boolean save(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            if (client.getId() == 0) {
                entityManager.persist(client);
            } else entityManager.merge(client);
            entityManager.getTransaction().commit();
        }
        catch (ConstraintViolationException e){
            entityManager.getTransaction().rollback();
            entityManager.close();
            return false;
        }
        return true;
    }

    public List<Client> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNamedQuery("Client.getAll",Client.class).getResultList();
    }
}
