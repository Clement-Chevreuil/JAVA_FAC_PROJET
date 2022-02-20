package com.example.application_entreprise_projet.METIER;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserMetier {

    //WITH BDD
    public void add(User u) throws SQLException;
    public User connexion(User u) throws SQLException;

    //NO BDD
    public User find(int id);
    public List<User> findAll ();
    public void update(User u);
    public void delete(User u);

}
