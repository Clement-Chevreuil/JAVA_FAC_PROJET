package com.example.application_entreprise_projet.METIER;

import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.DAO.IUserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserMetierImpl implements IUserMetier {

    private IUserDAO dao;


    public void setDao(IUserDAO dao) {
        this.dao = dao;
    }

    public void add(User u) throws SQLException {
        dao.addUser(u);
    }

    @Override
    public User connexion(User u) throws SQLException {
        return dao.connexionUser(u);
    }

    public User find(int id) {

        return dao.getUser(id);
    }

    public List<User> findAll() throws SQLException {

        return dao.getAllUsers();
    }

    @Override
    public List<User> findAllConsumers() throws SQLException {
        return dao.getAllConsumers();
    }

    @Override
    public List<User> findAllTraders() throws SQLException {
        return dao.getAllTraders();
    }

    public void update(User u) throws SQLException {
        dao.updateUser(u);

    }

    @Override
    public void validationTrader(int i) throws SQLException {
        dao.validateTrader(i);
    }

    public void delete(User u) {
        dao.deleteUser(u);

    }

}
