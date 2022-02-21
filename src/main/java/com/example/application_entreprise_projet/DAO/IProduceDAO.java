package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IProduceDAO {

    //WITH BDD
    public void addProduce(Produce p) throws SQLException;
    public Produce findProduce(Produce p) throws SQLException;
    public void updateProduce(Produce p) throws SQLException;
    public void deleteProduce(Produce p) throws SQLException;
    public List<Produce> findAllProduce() throws SQLException;
    public List<Produce> findAllProduceByUserID(User u) throws SQLException;
}
