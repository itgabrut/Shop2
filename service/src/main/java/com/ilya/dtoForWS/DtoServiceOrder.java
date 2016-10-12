package com.ilya.dtoForWS;

import com.ilya.dao.ItemRepository;
import com.ilya.dao.OrderRepository;
import com.ilya.model.Item;
import com.ilya.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

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

    @Transactional
    public List<TOrder> getBetween(String from,String to){
        List<Order> list = orderRepository.getBetween(from,to);
        List<TOrder> tOrderList = new ArrayList<>();
        for (Order or : list){
            tOrderList.add(createTorder(or));
        }
        return tOrderList;
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
//    int first, int pageSize, String sortField, String sortOrder,
    @Transactional
    public List<TOrder> getLazyList( Map<String, String> filters){
        String sortField =  filters.get("sortField");
        String sortOrder = filters.get("sortOrder");
            List<Order> list = orderRepository.getLazyList( filters.get("first") == null ? 0 : Integer.parseInt(filters.get("first")),
                    filters.get("pageSize") == null ? 4 : Integer.parseInt(filters.get("pageSize")),
                    filters.get("sortField"),
                    filters.get("sortOrder"),
                    filters);
            List<TOrder> listTOrders = new ArrayList<>();
            for(Order or : list){
                listTOrders.add(createTorder(or));
            }
        sortSum(sortField,sortOrder,listTOrders);
        if(filters.containsKey("sum")){
            for (TOrder tOrder : listTOrders){
                if(tOrder.getSum() == Integer.parseInt(filters.get("sum")))return Arrays.asList(tOrder);
            }
            return Collections.emptyList();
        }
        else return listTOrders;
    }

    private void sortSum(String sortField,String sortOrder,List<TOrder> listTOrders){
        if( sortField!=null && sortField.equals("sum")) {
            if("ASCEND".equals(sortOrder))
                listTOrders.sort(new Comparator<TOrder>() {
                    @Override
                    public int compare(TOrder o1, TOrder o2) {
                        return Integer.compare(o2.getSum(), o1.getSum());
                    }
                });
            if("DESCEND".equals(sortOrder))
                listTOrders.sort(new Comparator<TOrder>() {
                    @Override
                    public int compare(TOrder o1, TOrder o2) {
                        return Integer.compare(o1.getSum(), o2.getSum());
                    }
                });
        }
    }


}
