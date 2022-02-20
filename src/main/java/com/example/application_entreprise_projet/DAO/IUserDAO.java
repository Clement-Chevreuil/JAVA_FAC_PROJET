package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    public void addUser (User u) throws SQLException;
    public User connexionUser (User u) throws SQLException;
    public List<User> getAllUsers();
    public User getUser(int id);
    public void updateUser (User u);
    public void deleteUser (User u);
}
