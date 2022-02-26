package com.example.application_entreprise_projet.CLASS;

public class ShoppingBag {

    int id;
    User user;
    Produce produce;
    int noCommande;
    String dateCommandeValidate;
    String dateClickAndCollect;



    public ShoppingBag(int id, User user, Produce produce, int noCommande, String dateCommandeValidate, String dateClickAndCollect) {
        this.id = id;
        this.user = user;
        this.produce = produce;
        this.noCommande = noCommande;
        this.dateCommandeValidate = dateCommandeValidate;
        this.dateClickAndCollect = dateClickAndCollect;
    }

    public ShoppingBag(User user, Produce produce, int noCommande, String dateCommandeValidate, String dateClickAndCollect) {
        this.user = user;
        this.produce = produce;
        this.noCommande = noCommande;
        this.dateCommandeValidate = dateCommandeValidate;
        this.dateClickAndCollect = dateClickAndCollect;
    }

    public ShoppingBag(int id, User user, Produce produce, int noCommande, String dateCommandeValidate) {
        this.id = id;
        this.user = user;
        this.produce = produce;
        this.noCommande = noCommande;
        this.dateCommandeValidate = dateCommandeValidate;
    }

    public ShoppingBag(User user, Produce produce, int noCommande, String dateCommandeValidate) {
        this.user = user;
        this.produce = produce;
        this.noCommande = noCommande;
        this.dateCommandeValidate = dateCommandeValidate;
    }

    public ShoppingBag(int id, User userBag, Produce produceBag)
    {
        this.id = id;
        this.user = userBag;
        this.produce = produceBag;
    }
    public ShoppingBag(User user, Produce produce){ this.user = user; this.produce = produce;}
    public ShoppingBag(){}

    public Produce getProduce() {return produce;}
    public User getUser() {return user;}
    public int getId() {return id;}
    public int getNoCommande() {return noCommande;}
    public String getDateCommandeValidate() {return dateCommandeValidate;}
    public String getDateClickAndCollect() {return dateClickAndCollect;}

    public void setProduce(Produce produce) {this.produce = produce;}
    public void setUser(User user) {this.user = user;}
    public void setId(int id) {this.id = id;}
    public void setDateCommandeValidate(String dateCommandeValidate) {this.dateCommandeValidate = dateCommandeValidate;}
    public void setNoCommande(int noCommande) {this.noCommande = noCommande;}
    public void setDateClickAndCollect(String dateClickAndCollect) {this.dateClickAndCollect = dateClickAndCollect;}
}
