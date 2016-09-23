package com.ilya.service;

import com.ilya.dao.ClientRepository;
import com.ilya.dao.ClientRepositoryImpl;
import com.ilya.model.Client;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ilya on 20.08.2016.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    /**
     *  Get Client entity from db by Id
     * @param id Id
     * @return  Client entity
     */
    public Client getClient(int id) {
        Client client =  repository.getClient(id);
        return client;
    }

    /**
     *
     * @return List of all Client entities from db
     */
    public List<Client> getAll() {
        List<Client> list =  repository.getAll();
        return list;
    }

    /**
     *  Tries to remove Client entity from db
     * @param id Id
     * @return true if removed, otherwise false
     */
    @Transactional
    public boolean deleteClient(int id) {
        repository.deleteClient(id);
        return true;
    }

//    public boolean updateClient(Client client) {
//        return repository.save(client);
//    }

    /**
     * password Authentication
     * @param mail Email of Client r
     * @param pass Client's Password
     * @return Client entity, if found by email and password correct, null otherwise
     */
    public Client logIn(String mail,String pass){
        Client client = repository.getByEmail(mail);
        if(client == null)return null;
       return BCrypt.checkpw(pass,client.getPassword()) ? client : null;
    }


    /**
     * Redacts Client entity with or without password, or adds to db if Client is a new one
     * @param client Client entity
     * @return true on success
     */
    @Transactional
    public Client addClient(Client client) {
        if (client.getPassword().isEmpty() && client.getId() != 0) {
            Client old = repository.getClient(client.getId());
            client.setPassword(old.getPassword());

        } else {
            String hashed = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
            client.setPassword(hashed);
        }
        repository.save(client);
        return client;
    }
    /**
     *
     * @param mail Client's email
     * @return Client entity, or null
     */
    @Override
    public Client getByEmail(String mail) {
       Client client =  repository.getByEmail(mail);
        return client;
    }
}
