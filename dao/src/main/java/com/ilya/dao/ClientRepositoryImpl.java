package com.ilya.dao;



import com.ilya.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by ilya on 18.08.2016.
 * repo impl
 */
@Repository
@Transactional(readOnly = true)
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns Client instance from db
     * @param clientId Id of Client entity
     * @return          Client entity
     */
    public Client getClient(int clientId) {
        return entityManager.find(Client.class,clientId);
    }

    /**
     * Removes Client entity from db
     * @param clientId
     */
    @Transactional
    public void deleteClient(int clientId) {
        Client client = entityManager.find(Client.class,clientId);
        entityManager.remove(client);
    }

    /**
     *
     * @param email email
     * @return client
     */
    @Override
    public Client getByEmail(String email) {
        try {
            return entityManager.createQuery("select u from Client u where u.email =:email", Client.class).setParameter("email", email).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Client save(Client client){
        if (client.getId() == 0) {
            entityManager.persist(client);
            return client;
        }
        else return entityManager.merge(client);
    }

    public List<Client> getAll() {
        return entityManager.createNamedQuery("Client.getAll",Client.class).getResultList();
    }

//    public static void main(String[] args) {
//
//        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
//    }
}
