package com.example.application_entreprise_projet.DAO.SHOPPINGBAG;

import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingBagDAO {

    public void addShoppingBag (ShoppingBag shoppingBag) throws SQLException;
    public void updateShoppingBag (ShoppingBag shoppingBag) throws SQLException;
    public void deleteShoppingBag (ShoppingBag shoppingBag) throws SQLException;
    public List<ShoppingBag> getAllShoppingBagConsumer(User u) throws SQLException;
    public List<ShoppingBag> getAllShoppingBag() throws SQLException;
    public List<ShoppingBag> getAllShoppingBagValidateConsumer(User u) throws SQLException;
    public void commandValidation(ShoppingBag s) throws  SQLException;
    public List<ShoppingBag> findAllCommandToTrader(User u) throws SQLException;

    List<ShoppingBag> findAllCommandToConsumer(User u) throws SQLException;

    public List<ShoppingBag> getDetailsCommande(int noCommande) throws SQLException;
}
