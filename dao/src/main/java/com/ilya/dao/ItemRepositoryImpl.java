package com.ilya.dao;

import com.ilya.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
@Repository
@Transactional(readOnly = true)
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
   private EntityManager entityManager;

    public Item getItem(int itemId) {
        return entityManager.find(Item.class,itemId);
    }

    @Override
   public boolean IsUniqueName(String name) {
        long a = (long)entityManager.createQuery("select count(i.name) from Item i where i.name =:curr").setParameter("curr",name).getSingleResult();
       return a == 0;
    }

    @Transactional
    public void deleteItem(int itemId) {
        Item item = entityManager.find(Item.class,itemId);
        entityManager.remove(item);
    }

    public List<Item> getAll() {
        List<Item> list = entityManager.createQuery("select i from Item i",Item.class).getResultList();
        entityManager.close();
        return list;
    }

    @Transactional
    public void save(Item item) {
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
         return entityManager.createNamedQuery("Item.getThemes",String.class).getResultList();
    }

    public List<Item> getItemsByTheme(String theme){
        TypedQuery<Item> query = entityManager.createQuery("select i from Item i where i.theme = :nameOfTheme",Item.class);
        return query.setParameter("nameOfTheme",theme).getResultList();
    }

    public byte[] getFoto(int id){
      return  (byte[]) entityManager.createQuery("select i.foto from Item i where i.id =:id").setParameter("id",id).getSingleResult();
    }
}
