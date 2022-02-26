package com.example.application_entreprise_projet.DAO.USER;

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
    public void deleteUser (int id) throws SQLException;
    public void validateTrader(int i) throws SQLException;
    Integer StaticYear(String option1)throws SQLException;
    Integer StaticMonth(int option1, String option2)throws SQLException;
}
