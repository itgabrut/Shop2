package com.ilya.service;

import com.ilya.dao.ClientRepository;
import com.ilya.dao.ClientRepositoryImpl;
import com.ilya.model.Client;

import java.util.List;


/**
 * Created by ilya on 20.08.2016.
 */
public class ClientServiceImpl implements ClientService {

    ClientRepository repository = new ClientRepositoryImpl();

    public Client getClient(int id) {
        return repository.getClient(id);
    }

    public List<Client> getAll() {
        return repository.getAll();
    }

    public boolean deleteClient(int id) {
        return repository.deleteClient(id);
    }

    public boolean updateClient(Client client) {
        return repository.save(client);
    }

    public boolean addClient(Client client) {
        if (client.getAdress()==null){
            return false;
        }
        return repository.save(client);
    }
}
