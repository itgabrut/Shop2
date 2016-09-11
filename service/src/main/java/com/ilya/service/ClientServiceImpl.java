package com.ilya.service;

import com.ilya.dao.ClientRepository;
import com.ilya.dao.ClientRepositoryImpl;
import com.ilya.model.Client;
import org.mindrot.jbcrypt.BCrypt;

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

    public Client logIn(String mail,String pass){
        Client client = repository.getByEmail(mail);
       return BCrypt.checkpw(pass,client.getPassword()) ? client : null;
    }



    public boolean addClient(Client client) {
        if(client.getPassword().equals("") && client.getId()!=0){
           Client old =  repository.getClient(client.getId());
            client.setPassword(old.getPassword());
            return repository.save(client);
        }
        else {
            String hashed = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
            client.setPassword(hashed);
            return repository.save(client);
        }
    }

    @Override
    public Client getByEmail(String mail) {
       return repository.getByEmail(mail);
    }
}
