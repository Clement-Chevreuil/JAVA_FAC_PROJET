package com.example.application_entreprise_projet.DAO.USER;

import com.example.application_entreprise_projet.CLASS.User;

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

    public void add(User u) throws SQLException {
        RepoUsers.put(u.getId(), u);
        String sql = "INSERT INTO User (email, firstName, lastName , password, country, city, postCode, street, admin, trader, traderValidation, dateAdhesion) VALUES (?,?, ?, ?,?,?,?,?,false,?,false,NOW())";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, u.getEmail());
        statement.setString(2, u.getFirstName());
        statement.setString(3, u.getLastName());
        statement.setString(4, u.getPassword());
        statement.setString(5, u.getCountry());
        statement.setString(6, u.getCity());
        statement.setInt(7, u.getPostCode());
        statement.setString(8, u.getStreet());
        statement.setBoolean(9, u.getTrader());

        statement.executeUpdate();
        statement.close();
        disconnect();

    }

    public void update(User u) throws SQLException {

        String sql = "UPDATE User SET email = ?, firstName = ?, LastName = ?, password = ?,  country = ?, city = ?, postCode = ?, city = ?";
        sql += " WHERE id = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, u.getEmail());
        statement.setString(2, u.getFirstName());
        statement.setString(3, u.getLastName());
        statement.setString(4, u.getPassword());
        statement.setString(5, u.getCountry());
        statement.setString(6, u.getCity());
        statement.setInt(7, u.getPostCode());
        statement.setString(8, u.getCity());
        statement.setInt(9, u.getId());

        statement.executeUpdate();
        statement.close();
        disconnect();

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM user where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
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

    public List<User> findAll() throws SQLException {
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

    public List<User> findAllConsumers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE trader = 0 AND admin = 0";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery();

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

    public List<User> findAllTraders() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE trader = 1";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery();

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

    @Override
    public User connexion(User u) throws SQLException {
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
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            user = new User(id, email, password,city, postCode, country,  street, admin, trader, traderValidation, firstName, lastName);
        }

        statement.close();
        disconnect();

        return user;
    }

    @Override
    public User find(int id) {return RepoUsers.get(id);}


    @Override
    public Integer StaticYear(String option1) throws SQLException {
        Integer statistic = 0;

        String sql;

            sql = "SELECT COUNT(id) as stat FROM user WHERE YEAR(dateAdhesion) = ?;";
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, option1);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                int max = result.getInt("stat");
                statistic = max;
            }
            statement.close();
            disconnect();

        return statistic;
    }

    @Override
    public Integer StaticMonth(int option1, String option2) throws SQLException {
        Integer statistic = 0;

        String sql;
        System.out.println(option1);
        System.out.println(option2);
        sql = "SELECT COUNT(id) as stat FROM user WHERE MONTH (dateAdhesion) = ? AND YEAR(dateAdhesion) = ? ;";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, option1);
        statement.setString(2, option2);
        ResultSet result = statement.executeQuery();
        while (result.next())
        {
            int max = result.getInt("stat");
            statistic = max;
        }
        statement.close();
        disconnect();

        return statistic;
    }


    public void init() throws SQLException {
        //addUser(new User(1, "clement.chevreuil@gmail.com","password", "France", "Ligueil", 37240, "61 rue aristide briand", false, false ));
    }

}
