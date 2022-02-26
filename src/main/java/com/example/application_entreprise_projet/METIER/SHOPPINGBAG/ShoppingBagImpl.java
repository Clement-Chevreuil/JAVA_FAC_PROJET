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
    public void addBag(ShoppingBag shoppingBag) throws SQLException {
        dao.addShoppingBag(shoppingBag);
    }

    @Override
    public void updateBag(ShoppingBag shoppingBag) throws SQLException {
        dao.updateShoppingBag(shoppingBag);
    }

    @Override
    public void deleteBag(ShoppingBag shoppingBag) throws SQLException {
        dao.deleteShoppingBag(shoppingBag);
    }

    @Override
    public List<ShoppingBag> getAllBagConsumer(User u) throws SQLException {
        return dao.getAllShoppingBagConsumer(u);
    }

    @Override
    public List<ShoppingBag> getAllBagValidateConsumer(User u) throws SQLException {
        return dao.getAllShoppingBagValidateConsumer(u);
    }

    @Override
    public List<ShoppingBag> getAllBag() throws SQLException {
        return dao.getAllShoppingBag();
    }

    @Override
    public void bagValidation(ShoppingBag s) throws SQLException {
        dao.commandValidation(s);
    }

    @Override
    public List<ShoppingBag> findAllCommande(User u) throws SQLException {
        return dao.findAllCommandToTrader(u);
    }

    @Override
    public List<ShoppingBag> getDetailsCommande(int noCommande) throws SQLException {
        return dao.getDetailsCommande(noCommande);
    }

    @Override
    public List<ShoppingBag> findAllCommandToConsumer(User u) throws SQLException {
        return dao.findAllCommandToConsumer(u);
    }

}
