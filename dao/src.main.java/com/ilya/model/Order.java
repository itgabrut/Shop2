package com.ilya.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ilya.model.enums_utils.Delivery_status;
import com.ilya.model.enums_utils.Pay_status;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 20.08.2016.
 */

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payway")
    private String payway;

    @Column(name = "delivery")
    private String delivery;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private Delivery_status delivery_status;
    @Enumerated(EnumType.STRING)
    @Column(name = "pay_status")
    private Pay_status pay_status;
    @OneToMany(mappedBy = "order" ,fetch = FetchType.EAGER)
    private List<OrderForItem> orderForItems;

    public int getId() {
        return id;
    }

    public String getPayway() {
        return payway;
    }

    public String getDelivery() {
        return delivery;
    }

    public Client getClient() {
        return client;
    }

    public Delivery_status getDelivery_status() {
        return delivery_status;
    }

    public Pay_status getPay_status() {
        return pay_status;
    }

    public List<OrderForItem> getOrderForItems() {
        return orderForItems;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDelivery_status(Delivery_status delivery_status) {
        this.delivery_status = delivery_status;
    }

    public void setPay_status(Pay_status pay_status) {
        this.pay_status = pay_status;
    }

    public void setOrderForItems(List<OrderForItem> orderForItems) {
        this.orderForItems = orderForItems;
    }
}
