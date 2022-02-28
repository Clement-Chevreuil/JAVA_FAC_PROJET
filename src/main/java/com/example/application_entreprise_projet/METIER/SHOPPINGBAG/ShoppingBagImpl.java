package com.example.application_entreprise_projet.METIER.SHOPPINGBAG;

import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.DAO.SHOPPINGBAG.IShoppingBagDAO;

import java.sql.SQLException;
import java.util.List;

public class ShoppingBagImpl implements IShoppingBagMetier{

    private IShoppingBagDAO dao;

    public void setDao(IShoppingBagDAO dao) {
        this.dao = dao;
    }
    @Override
    public void add(ShoppingBag shoppingBag) throws SQLException {dao.add(shoppingBag);}
    @Override
    public void update(ShoppingBag shoppingBag) throws SQLException {dao.update(shoppingBag);}
    @Override
    public void delete(ShoppingBag shoppingBag) throws SQLException {dao.delete(shoppingBag);}
    @Override
    public void bagValidation(ShoppingBag s) throws SQLException {dao.bagValidation(s);}

    @Override
    public List<ShoppingBag> findAll() throws SQLException {return dao.findAll();}
    @Override
    public List<ShoppingBag> findByConsumer(User u) throws SQLException {return dao.findByConsumer(u);}
    @Override
    public List<ShoppingBag> findValidate(User u) throws SQLException {return dao.findValidate(u);}
    @Override
    public List<ShoppingBag> findCommandToTrader(User u) throws SQLException {return dao.findCommandToTrader(u);}
    @Override
    public List<ShoppingBag> findCommandToConsumer(User u) throws SQLException {return dao.findCommandToConsumer(u);}
    @Override
    public List<ShoppingBag> findCommandDetails(int noCommande) throws SQLException {return dao.findCommandDetails(noCommande);}


    @Override
    public int StatisticYear(String option) throws SQLException {return dao.StatisticYear(option);}
    @Override
    public int StatisticMonth(int i, String option) throws SQLException {return dao.StatisticMonth(i, option);}

}
