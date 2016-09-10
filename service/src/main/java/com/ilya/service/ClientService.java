package com.ilya.service;

import com.ilya.model.Client;

import java.util.List;

/**
 * Created by ilya on 20.08.2016.
 */
public interface ClientService {

    Client getClient(int id);

    List<Client> getAll();

    boolean deleteClient(int id);

    boolean updateClient(Client client);

    boolean addClient(Client client);

    Client getByEmail(String mail);
    Client logIn(String mail,String pass);
}
