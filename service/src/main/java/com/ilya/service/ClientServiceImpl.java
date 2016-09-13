package com.ilya.service;

import com.ilya.dao.ClientRepository;
import com.ilya.dao.ClientRepositoryImpl;
import com.ilya.model.Client;
import com.ilya.utils.EntManUtl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


/**
 * Created by ilya on 20.08.2016.
 */
public class ClientServiceImpl implements ClientService {

    ClientRepository repository = new ClientRepositoryImpl();

    public Client getClient(int id) {
        Client client =  repository.getClient(id);
        EntManUtl.closeEManager();
        return client;
    }

    public List<Client> getAll() {
        List<Client> list =  repository.getAll();
        EntManUtl.closeEManager();
        return list;
    }

    public boolean deleteClient(int id) {
        try {
            EntManUtl.startTransaction();
            repository.deleteClient(id);
            EntManUtl.commitTransaction();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            EntManUtl.rollback();
            return false;
        }
        finally {
            EntManUtl.closeEManager();
        }
    }

//    public boolean updateClient(Client client) {
//        return repository.save(client);
//    }

    public Client logIn(String mail,String pass){
        Client client = repository.getByEmail(mail);
        EntManUtl.closeEManager();
       return BCrypt.checkpw(pass,client.getPassword()) ? client : null;
    }



    public boolean addClient(Client client) {
        try {
            EntManUtl.startTransaction();
            if (client.getPassword().isEmpty() && client.getId() != 0) {
                Client old = repository.getClient(client.getId());
                client.setPassword(old.getPassword());

            } else {
                String hashed = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
                client.setPassword(hashed);
            }
            repository.save(client);
            EntManUtl.commitTransaction();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            EntManUtl.rollback();
            return false;
        }
        finally {
            EntManUtl.closeEManager();
        }
    }

    @Override
    public Client getByEmail(String mail) {
       Client client =  repository.getByEmail(mail);
        EntManUtl.closeEManager();
        return client;
    }
}
