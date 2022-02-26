package com.example.application_entreprise_projet.METIER.SHOPPINGBAG;

import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingBagMetier {

    public void addBag (ShoppingBag shoppingBag) throws SQLException;
    public void updateBag (ShoppingBag shoppingBag) throws SQLException;
    public void deleteBag (ShoppingBag shoppingBag) throws SQLException;
    public List<ShoppingBag> getAllBagConsumer(User u) throws SQLException;
    public List<ShoppingBag> getAllBagValidateConsumer(User u) throws SQLException;
    public List<ShoppingBag> getAllBag() throws SQLException;
    public void bagValidation (ShoppingBag s) throws SQLException;
    public List<ShoppingBag> findAllCommande(User u) throws  SQLException;
    public List<ShoppingBag> getDetailsCommande(int noCommande) throws SQLException;
    public List<ShoppingBag> findAllCommandToConsumer(User u) throws SQLException;

}
