package com.ilya.dao;



import com.ilya.model.Client;
import com.ilya.utils.EntManUtl;
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



    public Client getClient(int clientId) {
        EntityManager entityManager = EntManUtl.getEManager();
        return entityManager.find(Client.class,clientId);
    }
    public void deleteClient(int clientId) {
        EntityManager entityManager = EntManUtl.getEManager();
        Client client = entityManager.find(Client.class,clientId);
        entityManager.remove(client);
    }

    @Override
    public Client getByEmail(String email) {
        EntityManager entityManager = EntManUtl.getEManager();
       Client cl = (Client) entityManager.createQuery("select u from Client u where u.email =:email",Client.class).setParameter("email",email).getSingleResult();
        return cl;
    }

    public void save(Client client){
        EntityManager entityManager = EntManUtl.getEManager();
        if (client.getId() == 0) {
            entityManager.persist(client);
        }
        else entityManager.merge(client);
    }

    public List<Client> getAll() {
        EntityManager entityManager = EntManUtl.getEManager();
        return entityManager.createNamedQuery("Client.getAll",Client.class).getResultList();
    }
}
