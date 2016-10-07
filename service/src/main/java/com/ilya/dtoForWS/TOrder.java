package com.ilya.dtoForWS;





import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;
import java.util.List;

/**
 * Created by ilya on 05.10.2016.
 */

public class TOrder {


    private int id;


    private int summ;


    private Date date;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private List<TItem> items;

    public TOrder(int id, int summ, Date date, List<TItem> items) {
        this.id = id;
        this.summ = summ;
        this.date = date;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public int getSumm() {
        return summ;
    }

    public Date getDate() {
        return date;
    }

    public List<TItem> getItems() {
        return items;
    }
}
