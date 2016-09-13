package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.utils.EntManUtl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public class ItemRepositoryImpl implements ItemRepository {


    @Override
    public void saveWithoutFoto(Item item) {
        Item old = getItem(item.getId());
        item.setFoto(old.getFoto());
        item.setVersion(old.getVersion());
        save(item);
    }

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
        if(item.getId()==0) {
            long a = (long) entityManager.createQuery("select count(i.name) from Item i where i.name =:curr").setParameter("curr", item.getName()).getSingleResult();
            if (a < 1) {
                entityManager.persist(item);
            }
        }
        else entityManager.merge(item);
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
}
