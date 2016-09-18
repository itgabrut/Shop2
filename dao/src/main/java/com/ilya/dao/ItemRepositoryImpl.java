package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.utils.EntManUtl;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public class ItemRepositoryImpl implements ItemRepository {


    public Item getItem(int itemId) {
        EntityManager entityManager = EntManUtl.getEManager();
        return entityManager.find(Item.class,itemId);
    }

    public void deleteItem(int itemId) {
        EntityManager entityManager = EntManUtl.getEManager();
        Item item = entityManager.find(Item.class,itemId);
        entityManager.remove(item);
    }

    public List<Item> getAll() {
        EntityManager entityManager = EntManUtl.getEManager();
        List<Item> list = entityManager.createQuery("select i from Item i",Item.class).getResultList();
        EntManUtl.closeEManager();
        return list;
    }

    public void save(Item item) {
        EntityManager entityManager = EntManUtl.getEManager();
        if(item.getProxyId()==0) {
            long a = (long) entityManager.createQuery("select count(i.name) from Item i where i.name =:curr").setParameter("curr", item.getName()).getSingleResult();
            if (a < 1) {
                entityManager.persist(item);
            }
        }
        else {
            entityManager.clear();
            Item it = entityManager.find(Item.class,item.getProxyId());
            entityManager.lock(it, LockModeType.OPTIMISTIC);
//            item.setVersion(old.getVersion());
            it.setName(item.getName());
            it.setQuantity(item.getQuantity());
            it.setDescription(item.getDescription());
            if(item.getFoto()!=null) it.setFoto(item.getFoto());
            it.setPrice(item.getPrice());
            it.setTheme(item.getTheme());
            entityManager.flush();
//            entityManager.merge(it);
        }
    }

    public List<String> getThemes(){
        EntityManager entityManager = EntManUtl.getEManager();
         return entityManager.createNamedQuery("Item.getThemes",String.class).getResultList();
    }

    public List<Item> getItemsByTheme(String theme){
        EntityManager entityManager = EntManUtl.getEManager();
        TypedQuery<Item> query = entityManager.createQuery("select i from Item i where i.theme = :nameOfTheme",Item.class);
        return query.setParameter("nameOfTheme",theme).getResultList();
    }

    public byte[] getFoto(int id){
        EntityManager entityManager = EntManUtl.getEManager();
      return  (byte[]) entityManager.createQuery("select i.foto from Item i where i.id =:id").setParameter("id",id).getSingleResult();
    }
}
