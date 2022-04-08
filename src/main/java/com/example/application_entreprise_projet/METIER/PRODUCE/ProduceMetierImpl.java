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
    @Override
    public void add(Produce p) throws SQLException {dao.add(p);}
    @Override
    public void update(Produce p) throws SQLException {dao.update(p);}
    @Override
    public void delete(int id) throws SQLException {dao.delete(id);}

    @Override
    public List<Produce> findAll() throws SQLException {return dao.findAll();}
    @Override
    public List<Produce> findNotReserve(User u) throws SQLException {return dao.findProduceNotReserve(u);}
    @Override
    public List<Produce> findByUserID(User u) throws SQLException {return dao.findByUserID(u);}
    @Override
    public Produce find(int id) throws SQLException {return dao.find(id);}



}
