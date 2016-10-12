package com.ilya.dtoForWS;





import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utils.jsonutils.CustomDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * Created by ilya on 05.10.2016.
 */

public class TOrder {


    private int id;


    private int sum;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date date;


    private List<TItem> items;

    public TOrder(int id, int sum, Date date, List<TItem> items) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public int getSum() {
        return sum;
    }

    public Date getDate() {
        return date;
    }

    public List<TItem> getItems() {
        return items;
    }
}
