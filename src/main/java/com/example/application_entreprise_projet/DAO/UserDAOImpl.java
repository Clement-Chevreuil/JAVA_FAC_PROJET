package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.User;

import javax.servlet.http.HttpSession;
import java.sql.*;
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
        String sql = "INSERT INTO User (id, email , password, country, city, postCode, street, admin, trader, traderValidation) VALUES (?, ?, ?,?,?,?,?,false,?,false)";
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

    @Override
    public User connexionUser(User u) throws SQLException {
        User user = null;


        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, u.getEmail());
        statement.setString(2, u.getPassword());

        ResultSet result = statement.executeQuery();

        if (result.next()) {

            int id = result.getInt("id");
            String email = result.getString("email");
            String password = result.getString("password");
            String country = result.getString("country");
            String city = result.getString("city");
            int postCode = result.getInt("postCode");
            String street = result.getString("street");
            Boolean admin = result.getBoolean("admin");
            Boolean trader = result.getBoolean("trader");
            Boolean traderValidation = result.getBoolean("traderValidation");
            user = new User(id, email, password, country, city, postCode, street, admin, trader, traderValidation);
        }

        statement.close();
        disconnect();

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("id");
            String email = result.getString("email");
            String password = result.getString("password");
            String country = result.getString("country");
            String city = result.getString("city");
            int postCode = result.getInt("postCode");
            String street = result.getString("street");
            Boolean admin = result.getBoolean("admin");
            Boolean trader = result.getBoolean("trader");
            Boolean traderValidation = result.getBoolean("traderValidation");
            User user = new User(id, email, password, country, city, postCode, street, admin, trader, traderValidation);
            listUser.add(user);
        }

        statement.close();
        disconnect();

        return listUser;
    }

    public List<User> getAllTraders() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("id");
            String email = result.getString("email");
            String password = result.getString("password");
            String country = result.getString("country");
            String city = result.getString("city");
            int postCode = result.getInt("postCode");
            String street = result.getString("street");
            Boolean admin = result.getBoolean("admin");
            Boolean trader = result.getBoolean("trader");
            Boolean traderValidation = result.getBoolean("traderValidation");
            User user = new User(id, email, password, country, city, postCode, street, admin, trader, traderValidation);
            listUser.add(user);
        }

        statement.close();
        disconnect();

        return listUser;
    }

    public List<User> getAllConsumers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE trader = 0 AND admin = 0";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("id");
            String email = result.getString("email");
            String password = result.getString("password");
            String country = result.getString("country");
            String city = result.getString("city");
            int postCode = result.getInt("postCode");
            String street = result.getString("street");
            Boolean admin = result.getBoolean("admin");
            Boolean trader = result.getBoolean("trader");
            Boolean traderValidation = result.getBoolean("traderValidation");
            User user = new User(id, email, password, country, city, postCode, street, admin, trader, traderValidation);
            listUser.add(user);
        }

        statement.close();
        disconnect();

        return listUser;
    }

    public User getUser(int id) {

        return RepoUsers.get(id);
    }

    public void updateUser(User u) throws SQLException {
        RepoUsers.put(u.getId(),u);
        String sql = "UPDATE User SET email = ?, password = ?, admin = false, trader = ?, country = ?, city = ?, postCode = ?, city = ?, traderValidation = ?";
        sql += " WHERE user = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, u.getEmail());
        statement.setString(2, u.getPassword());
        statement.setBoolean(3, u.getTrader());
        statement.setString(4, u.getCountry());
        statement.setString(5, u.getCity());
        statement.setInt(6, u.getPostCode());
        statement.setString(7, u.getCity());
        statement.setBoolean(8, u.getTraderValidation());
        statement.setInt(9, u.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

    }

    public void validateTrader(int id) throws SQLException {
        String sql = "UPDATE User SET traderValidation = 1";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

    }

    @Override
    public void deleteUser(User u) {
        RepoUsers.remove(u.getId());
    }

    public void init() throws SQLException {
        //addUser(new User(1, "clement.chevreuil@gmail.com","password", "France", "Ligueil", 37240, "61 rue aristide briand", false, false ));
    }

}
