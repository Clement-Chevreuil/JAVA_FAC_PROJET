package com.example.application_entreprise_projet.DAO;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProduceDAOImpl implements IProduceDAO{

    private Map<Integer, Produce> RepoProduce = new HashMap<Integer, Produce>();

    private String jdbcURL = "jdbc:mysql://localhost:3306/application_entreprise_projet";
    private String jdbcUsername = "root";
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ProduceDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = "";
    }

    public ProduceDAOImpl() {}

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

    @Override
    public void addProduce(Produce p) throws SQLException {
        System.out.println("hello");
        RepoProduce.put(p.getId(), p);
        String sql = "INSERT INTO produce (id, name, category, price, expirationDate, userId) VALUES (?,?,?,?,?,?)";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, p.getId());
        statement.setString(2, p.getName());
        statement.setString(3, p.getCategory());
        statement.setDouble(4, p.getPrice());
        statement.setDate(5, Date.valueOf(p.getExpirationDate()));
        statement.setInt(6, p.getUser().getId());

        statement.executeUpdate();
        statement.close();
        disconnect();
    }

    @Override
    public Produce findProduce(Produce p) throws SQLException {
        Produce produce = new Produce();
        return produce;
    }

    @Override
    public void updateProduce(Produce p) throws SQLException {

    }

    @Override
    public void deleteProduce(Produce p) throws SQLException {

    }

    @Override
    public List<Produce> findAllProduce() throws SQLException {
        List<Produce> listProduce = new ArrayList<>();
        String sql = "SELECT * FROM produce";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            String name = result.getString("name");
            String category = result.getString("category");
            Double price = result.getDouble("price");
            String expirationDate = result.getString("expirationDate");
            Produce produce = new Produce(id, name, category, price, expirationDate);
            listProduce.add(produce);
        }

        statement.close();
        disconnect();

        return listProduce;
    }

    @Override
    public List<Produce> findAllProduceByUserID(User u) throws SQLException {
        List<Produce> listProduce = new ArrayList<>();
        String sql = "SELECT * FROM produce WHERE userId = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            String name = result.getString("name");
            String category = result.getString("category");
            Double price = result.getDouble("price");
            String expirationDate = result.getString("expirationDate");
            Produce produce = new Produce(id, name, category, price, expirationDate);
            listProduce.add(produce);
        }

        statement.close();
        disconnect();

        return listProduce;
    }

    private void init() {
    }
}
