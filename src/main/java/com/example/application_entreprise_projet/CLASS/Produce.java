package com.example.application_entreprise_projet.CLASS;

import java.util.Date;
import java.util.List;

public class Produce {

    int id;
    String name;
    String category;
    double price;
    String expirationDate;
    User user;
    List<ShoppingBag> shoppingBagList;
    int sold;

    public Produce(int id, String name, String category, double price, String expirationDate, User user, int sold) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.expirationDate = expirationDate;
        this.user = user;
        this.sold = sold;
    }

    public Produce(int id, String name, String category, double price, String expirationDate, int sold) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.expirationDate = expirationDate;
        this.sold = sold;
    }

    public Produce(int id, String name, String category, double price, String expirationDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Produce(){}

    //GETTER

    public int getId() {return id;}
    public String getName() {return name;}
    public String getCategory() {return category;}
    public double getPrice() {return price;}
    public String getExpirationDate() {return expirationDate;}
    public User getUser() {return user;}
    public List<ShoppingBag> getShoppingBagList() {return shoppingBagList;}
    public int getSold() {return sold;}
    //SETTER

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setPrice(double price) {this.price = price;}
    public void setExpirationDate(String expirationDate) {this.expirationDate = expirationDate;}
    public void setUser(User user) {this.user = user;}
    public void setShoppingBagList(List<ShoppingBag> shoppingBagList) {this.shoppingBagList = shoppingBagList;}
    public void setSold(int sold) {this.sold = sold;}
}
