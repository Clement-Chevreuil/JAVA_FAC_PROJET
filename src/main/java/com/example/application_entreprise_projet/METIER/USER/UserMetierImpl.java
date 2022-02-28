package com.example.application_entreprise_projet.METIER.USER;

import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.DAO.USER.IUserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserMetierImpl implements IUserMetier {

    private IUserDAO dao;


    public void setDao(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void add(User u) throws SQLException {dao.add(u);}
    @Override
    public void update(User u) throws SQLException {dao.update(u);}
    @Override
    public void delete(int id) throws SQLException {dao.delete(id);}
    @Override
    public void validationTrader(int i) throws SQLException {dao.validateTrader(i);}


    @Override
    public List<User> findAll() throws SQLException {return dao.findAll();}
    @Override
    public List<User> findAllConsumers() throws SQLException {return dao.findAllConsumers();}
    @Override
    public List<User> findAllTraders() throws SQLException {return dao.findAllTraders();}


    @Override
    public User connexion(User u) throws SQLException {return dao.connexion(u);}
    @Override
    public User find(int id) {return dao.find(id);}


    @Override
    public Integer StatisticYear(String option1) throws SQLException {return dao.StaticYear(option1);}
    @Override
    public Integer StatisticMonth(int option1, String option2) throws SQLException {return dao.StaticMonth(option1, option2);}

}
