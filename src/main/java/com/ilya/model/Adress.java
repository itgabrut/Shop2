package com.ilya.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by ilya on 20.08.2016.
 */
@Embeddable
 public class Adress {

   public Adress() {
   }

   public Adress(String country, String city, String zip, String street, String house, String app) {
      this.country = country;
      this.city = city;
      this.zip = zip;
      this.street = street;
      this.house = house;
      this.app = app;
   }

   @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "zip")
    private String zip;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "app")
    private String app;

   public String getCountry() {
      return country;
   }

   public String getCity() {
      return city;
   }

   public String getZip() {
      return zip;
   }

   public String getStreet() {
      return street;
   }

   public String getHouse() {
      return house;
   }

   public String getApp() {
      return app;
   }
}
