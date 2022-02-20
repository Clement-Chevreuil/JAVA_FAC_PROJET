package com.example.application_entreprise_projet.CLASS;

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

    //CONSTRUCTOR

    public User(int id, String email, String password, String country, String city, int postCode, String street, Boolean admin, Boolean trader) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.admin = admin;
        this.trader = trader;
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
}
