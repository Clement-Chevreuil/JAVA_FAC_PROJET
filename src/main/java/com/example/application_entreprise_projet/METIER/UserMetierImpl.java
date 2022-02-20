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

    public List<User> findAll() {

        return dao.getAllUsers();
    }

    public void update(User u) {
        dao.updateUser(u);

    }

    public void delete(User u) {
        dao.deleteUser(u);

    }

}
