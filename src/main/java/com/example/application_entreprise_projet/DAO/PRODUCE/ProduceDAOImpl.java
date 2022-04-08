package com.example.application_entreprise_projet.DAO.PRODUCE;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduceDAOImpl implements IProduceDAO{

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
    public void add(Produce p) throws SQLException {

        String sql = "INSERT INTO produce (name, category, price, expirationDate, userId, sold) VALUES (?,?,?,?,?, 0)";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, p.getName());
        statement.setString(2, p.getCategory());
        statement.setDouble(3, p.getPrice());
        statement.setDate(4, Date.valueOf(p.getExpirationDate()));
        statement.setInt(5, p.getUser().getId());

        statement.executeUpdate();
        statement.close();
        disconnect();
    }
    @Override
    public void update(Produce p ) throws SQLException {
        System.out.println("hello2");
        String sql = "UPDATE produce SET name = ?, category = ?,  price = ?, expirationDate = ? WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, p.getName());
        statement.setString(2, p.getCategory());
        statement.setDouble(3, p.getPrice());
        statement.setString(4, p.getExpirationDate());
        statement.setInt(5, p.getId());

        statement.executeUpdate();
        statement.close();
        disconnect();

    }
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM produce where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
    }

    @Override
    public List<Produce> findAll() throws SQLException {
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
            int sold = result.getInt("sold");
            Produce produce = new Produce(id, name, category, price, expirationDate, sold);
            listProduce.add(produce);
        }

        statement.close();
        disconnect();

        return listProduce;
    }
    @Override
    public List<Produce> findByUserID(User u) throws SQLException {
        List<Produce> listProduce = new ArrayList<>();
        String sql = "SELECT p.id, name, category, price, expirationDate, sold FROM produce p WHERE userId = ? ORDER BY sold asc";

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
            int sold = result.getInt("sold");
            Produce produce = new Produce(id, name, category, price, expirationDate, sold);
            listProduce.add(produce);
        }

        statement.close();
        disconnect();

        return listProduce;
    }
    @Override
    public List<Produce> findProduceNotReserve(User u) throws SQLException {
        List<Produce> listProduce = new ArrayList<>();
        String sql = "SELECT * FROM produce WHERE id NOT IN (SELECT idProduce FROM shoppingbag WHERE idUser = ? ) AND sold = 0 ";

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
            int sold = result.getInt("sold");
            Produce produce = new Produce(id, name, category, price, expirationDate, sold);
            listProduce.add(produce);
        }

        statement.close();
        disconnect();

        return listProduce;
    }

    @Override
    public Produce find(int id) throws SQLException {

        Produce producenew = new Produce();
        String sql = "SELECT * FROM produce WHERE id = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();


        if (result.next()) {

            int id2 = result.getInt("id");
            String name = result.getString("name");
            String category = result.getString("category");
            Double price = result.getDouble("price");
            String expirationDate = result.getString("expirationDate");
            int sold = result.getInt("sold");
            producenew = new Produce(id2, name, category, price, expirationDate, sold);

        }

        statement.close();
        disconnect();

        return producenew;
    }

    private void init() {}
}
