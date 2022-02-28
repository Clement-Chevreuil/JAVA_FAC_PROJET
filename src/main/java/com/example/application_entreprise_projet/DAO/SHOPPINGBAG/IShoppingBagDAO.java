package com.example.application_entreprise_projet.DAO.SHOPPINGBAG;

import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingBagDAO {

    public void add (ShoppingBag shoppingBag) throws SQLException;
    public void update (ShoppingBag shoppingBag) throws SQLException;
    public void delete (ShoppingBag shoppingBag) throws SQLException;
    public void bagValidation(ShoppingBag s) throws  SQLException;

    public List<ShoppingBag> findAll() throws SQLException;
    public List<ShoppingBag> findByConsumer(User u) throws SQLException;
    public List<ShoppingBag> findValidate(User u) throws SQLException;
    public List<ShoppingBag> findCommandToTrader(User u) throws SQLException;
    public List<ShoppingBag> findCommandToConsumer(User u) throws SQLException;
    public List<ShoppingBag> findCommandDetails(int noCommande) throws SQLException;

    public int StatisticYear(String option) throws SQLException;
    public int StatisticMonth(int i, String option) throws SQLException;
}
