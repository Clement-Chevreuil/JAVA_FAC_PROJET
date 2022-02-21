package com.example.application_entreprise_projet.METIER;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserMetier {

    //WITH BDD
    public void add(User u) throws SQLException;
    public User connexion(User u) throws SQLException;
    public List<User> findAll () throws SQLException;
    public List<User> findAllConsumers () throws SQLException;
    public List<User> findAllTraders () throws SQLException;
    public void update(User u) throws SQLException;
    public void validationTrader(int i) throws SQLException;

    //NO BDD
    public User find(int id);

    public void delete(User u);

}
