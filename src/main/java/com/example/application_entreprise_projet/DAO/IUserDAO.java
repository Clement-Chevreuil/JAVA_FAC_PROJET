package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    public void addUser (User u) throws SQLException;
    public User connexionUser (User u) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public List<User> getAllTraders() throws SQLException;
    public List<User> getAllConsumers() throws SQLException;
    public User getUser(int id);
    public void updateUser (User u) throws SQLException;
    public void deleteUser (User u);
    public void validateTrader(int i) throws SQLException;
}
