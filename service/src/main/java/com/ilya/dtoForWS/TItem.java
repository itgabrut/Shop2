package com.ilya.dtoForWS;


/**
 * Created by ilya on 05.10.2016.
 * DTO
 */
    class TItem {

    private String name;

    private int quantity;

    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
