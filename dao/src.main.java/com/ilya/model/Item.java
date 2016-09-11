package com.ilya.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;



/**
 * Created by ilya on 20.08.2016.
 */
@NamedQueries({
        @NamedQuery(name = "Item.getThemes",query = "select distinct i.theme from Item i order by i.theme")
})
@Entity
@Table(name = "items")
@Access(AccessType.FIELD)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Version
    @Column(name = "version" , nullable = false, columnDefinition = "integer default 0")
    private long version;
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
    @Lob
    @JsonIgnore
    private byte[] foto;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

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

    public byte[] getFoto() {
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

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
