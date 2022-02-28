package com.example.application_entreprise_projet.DAO.USER;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    public void add (User u) throws SQLException;
    public void update (User u) throws SQLException;
    public void delete (int id) throws SQLException;
    public void validateTrader(int i) throws SQLException;

    public List<User> findAll() throws SQLException;
    public List<User> findAllConsumers() throws SQLException;
    public List<User> findAllTraders() throws SQLException;

    public User connexion (User u) throws SQLException;
    public User find(int id);

    Integer StaticYear(String option1)throws SQLException;
    Integer StaticMonth(int option1, String option2)throws SQLException;
}
