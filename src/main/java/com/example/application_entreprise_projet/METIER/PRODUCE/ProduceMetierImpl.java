package com.example.application_entreprise_projet.METIER.PRODUCE;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.DAO.PRODUCE.IProduceDAO;

import java.sql.SQLException;
import java.util.List;

public class ProduceMetierImpl implements IProduceMetier {

    private IProduceDAO dao;

    public void setDao(IProduceDAO dao) {
        this.dao = dao;
    }

    public void add(Produce p) throws SQLException {
        dao.addProduce(p);
    }


    @Override
    public Produce find(int id) throws SQLException {
        Produce produce = dao.findProduce(id);
        return produce;
    }

    @Override
    public void update(Produce p) throws SQLException {
        dao.updateProduce(p);
    }

    @Override
    public void delete(int id) throws SQLException {
        dao.deleteProduce(id);
    }

    @Override
    public List<Produce> findAll() throws SQLException {
        return dao.findAllProduce();
    }

    @Override
    public List<Produce> findAllNotReserve(User u) throws SQLException {
        return dao.findAllProduceNotReserve(u);
    }

    @Override
    public List<Produce> findAllByUserID(User u) throws SQLException {
        return dao.findAllProduceByUserID(u);
    }



}
