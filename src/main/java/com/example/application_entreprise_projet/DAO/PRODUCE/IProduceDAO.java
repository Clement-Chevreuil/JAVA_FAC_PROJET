package com.example.application_entreprise_projet.DAO.PRODUCE;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IProduceDAO {

    //WITH BDD
    public void add(Produce p) throws SQLException;
    public void update(Produce p) throws SQLException;
    public void delete(int id) throws SQLException;


    public List<Produce> findAll() throws SQLException;
    public List<Produce> findByUserID(User u) throws SQLException;
    public List<Produce> findProduceNotReserve(User u) throws SQLException;

    public Produce find(int id) throws SQLException;
}
