package com.example.application_entreprise_projet.CLASS;

import java.util.List;

public class User {

    int id;
    String email;
    String password;
    String city;
    int postCode;
    String street;
    String country;
    Boolean admin;
    Boolean trader;
    Boolean traderValidation;
    List<ShoppingBag> shoppingBag;
    String dateAdhesion;
    String firstName;
    String lastName;

    //CONSTRUCTOR



    public User(int id, String firstName, String lastName, String email, String country, String city, int postCode, String street) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.admin = admin;
        this.trader = trader;
        this.traderValidation = traderValidation;
    }

    public User(int id, String email, String password, String country, String city, int postCode, String street, Boolean admin, Boolean trader, Boolean traderValidation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.admin = admin;
        this.trader = trader;
        this.traderValidation = traderValidation;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }

    public User(){};

    //GETTER

    public int getId() {return id;}
    public String getPassword() {return password;}
    public Boolean getAdmin() {return admin;}
    public Boolean getTrader() {return trader;}
    public String getEmail() {return email;}
    public int getPostCode() {return postCode;}
    public String getCity() {return city;}
    public String getStreet() {return street;}
    public String getCountry() {return country;}
    public Boolean getTraderValidation() {return traderValidation;}
    public List<ShoppingBag> getShoppingBag() {return shoppingBag;}
    public String getDateAdhesion() {return dateAdhesion;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    //SETTER

    public void setId(int id) {this.id = id;}
    public void setPassword(String password) {this.password = password;}
    public void setAdmin(Boolean admin) {this.admin = admin;}
    public void setEmail(String email) {this.email = email;}
    public void setTrader(Boolean trader) {this.trader = trader;}
    public void setCity(String city) {this.city = city;}
    public void setCountry(String country) {this.country = country;}
    public void setPostCode(int postCode) {this.postCode = postCode;}
    public void setStreet(String street) {this.street = street;}
    public void setTraderValidation(Boolean traderValidation) {this.traderValidation = traderValidation;}
    public void setShoppingBag(List<ShoppingBag> shoppingBag) {this.shoppingBag = shoppingBag;}
    public void setDateAdhesion(String dateAdhesion) {this.dateAdhesion = dateAdhesion;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
}
