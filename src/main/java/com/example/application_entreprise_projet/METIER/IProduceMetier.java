package com.example.application_entreprise_projet.METIER;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IProduceMetier {

    //WITH BDD
    public void add(Produce p) throws SQLException;
    public Produce find(Produce p) throws SQLException;
    public void update(Produce p) throws SQLException;
    public void delete(Produce p) throws SQLException;
    List<Produce> findAll() throws SQLException;
    List<Produce> findAllByUserID(User u) throws SQLException;
}
