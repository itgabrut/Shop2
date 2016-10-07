package com.ilya.dtoForWS;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.OrderRepository;
import com.ilya.model.Item;
import com.ilya.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 05.10.2016.
 */

@Service
public class DtoServiceOrder {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public TOrder getTOrder(int orderId){
        Order order = orderRepository.getById(orderId);
        return createTorder(order);
    }

    @Transactional
    public List<TOrder> getList(){
        List<Order> list = orderRepository.getAll();
        List<TOrder> listTOrders = new ArrayList<>();
        for(Order or : list){
            listTOrders.add(createTorder(or));
        }
        return listTOrders;
    }

    private TOrder createTorder(Order order){
        Map<Item,Integer> map = orderRepository.getItemsOfOrder(order.getId());
        int sum = 0;
        List<TItem> list = new ArrayList<>();
        for(Map.Entry<Item,Integer> entr : map.entrySet()){
            TItem tItem = new TItem();
            tItem.setName(entr.getKey().getName());
            tItem.setPrice(entr.getKey().getPrice());
            tItem.setQuantity(entr.getValue());
            list.add(tItem);
            sum += (tItem.getPrice() * tItem.getQuantity());
        }
        return new TOrder(order.getId(),sum,order.getDate(),list);
    }


}
