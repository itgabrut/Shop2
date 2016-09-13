package com.ilya.dao;

import com.ilya.model.Client;

import java.util.List;

/**
 * Created by ilya on 20.08.2016.
 */
public interface ClientRepository {

    Client getClient(int clientId);

    void deleteClient(int clientId);

    List<Client> getAll();

    void save(Client client);

    Client getByEmail(String mail);
}
