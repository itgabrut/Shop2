package com.ilya.model;

import javax.persistence.*;
import java.io.File;

/**
 * Created by ilya on 20.08.2016.
 */
@Entity
@Table(name = "items")
@Access(AccessType.FIELD)
public class Item {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "theme")
    private String theme;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "foto")
    private File foto;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getTheme() {
        return theme;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public File getFoto() {
        return foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }
}
