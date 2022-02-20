package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class UserDAOImpl implements IUserDAO {


    private Map<Integer, User> RepoUsers = new HashMap<Integer, User>();

    private String jdbcURL = "jdbc:mysql://localhost:3306/application_entreprise_projet";
    private String jdbcUsername = "root";
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = "";
    }

    public UserDAOImpl() {}

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public void addUser(User u) throws SQLException {
        RepoUsers.put(u.getId(), u);
        String sql = "INSERT INTO User (id, email , password, country, city, postCode, street, admin, trader) VALUES (?, ?, ?,?,?,?,?,false,?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());
        statement.setString(2, u.getEmail());
        statement.setString(3, u.getPassword());
        statement.setString(4, u.getCountry());
        statement.setString(5, u.getCity());
        statement.setInt(6, u.getPostCode());
        statement.setString(7, u.getStreet());
        statement.setBoolean(8, u.getTrader());

        statement.executeUpdate();
        statement.close();
        disconnect();

    }

    public List<User> getAllUsers() {
        Collection<User> res= RepoUsers.values();
        return new ArrayList<User>(res);
    }

    public User getUser(int id) {

        return RepoUsers.get(id);
    }

    public void updateUser(User u) {
        RepoUsers.put(u.getId(),u);

    }

    @Override
    public void deleteUser(User u) {
        RepoUsers.remove(u.getId());
    }

    public void init() throws SQLException {
        //addUser(new User(1, "clement.chevreuil@gmail.com","password", "France", "Ligueil", 37240, "61 rue aristide briand", false, false ));
    }

}
