package com.example.application_entreprise_projet.METIER.PRODUCE;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IProduceMetier {

    //WITH BDD
    public void add(Produce p) throws SQLException;
    public void update(Produce p) throws SQLException;
    public void delete(int id) throws SQLException;

    List<Produce> findAll() throws SQLException;
    List<Produce> findNotReserve(User u) throws SQLException;
    List<Produce> findByUserID(User u) throws SQLException;

    public Produce find(int id) throws SQLException;
}
