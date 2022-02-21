package com.example.application_entreprise_projet.METIER;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.DAO.IProduceDAO;
import com.example.application_entreprise_projet.DAO.IUserDAO;
import com.example.application_entreprise_projet.DAO.ProduceDAOImpl;
import com.example.application_entreprise_projet.DAO.UserDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ProduceMetierImpl implements IProduceMetier{

    private IProduceDAO dao;

    public void setDao(IProduceDAO dao) {
        this.dao = dao;
    }

    public void add(Produce p) throws SQLException {
        dao.addProduce(p);
    }

    @Override
    public Produce find(Produce p) throws SQLException {
        Produce produce = dao.findProduce(p);
        return produce;
    }

    @Override
    public void update(Produce p) throws SQLException {
        dao.updateProduce(p);
    }

    @Override
    public void delete(Produce p) throws SQLException {
        dao.deleteProduce(p);
    }

    @Override
    public List<Produce> findAll() throws SQLException {
        return dao.findAllProduce();
    }

    @Override
    public List<Produce> findAllByUserID(User u) throws SQLException {
        return dao.findAllProduceByUserID(u);
    }



}
