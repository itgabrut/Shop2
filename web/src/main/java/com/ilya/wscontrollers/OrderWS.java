package com.ilya.wscontrollers;

import com.ilya.dtoForWS.DtoServiceOrder;
import com.ilya.dtoForWS.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 05.10.2016.
 */
@RestController
@RequestMapping(value = "/ws")
public class OrderWS {

    @Autowired
    DtoServiceOrder dtoServiceOrder;

    @RequestMapping(value = "/single",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
        public TOrder getOrder(){
        TOrder tOrder = dtoServiceOrder.getTOrder(54);
            return tOrder;
        }

    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TOrder> getList(){
        return dtoServiceOrder.getList();
    }

//    @RequestParam("first") int first,
//    @RequestParam("pageSize")int pageSize,
//    @RequestParam("sortField")String sortField,
//    @RequestParam("sortOrder")String sortOrder,
    @RequestMapping(value = "/lazy",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TOrder> getLazy(@RequestParam Map<String, String> params){
        return dtoServiceOrder.getLazyList(params);
    }

    @RequestMapping(value = "/between",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TOrder> getBetween(@RequestParam("from") String from,
                                   @RequestParam("to") String to){
            return dtoServiceOrder.getBetween(from,to);
    }
}
