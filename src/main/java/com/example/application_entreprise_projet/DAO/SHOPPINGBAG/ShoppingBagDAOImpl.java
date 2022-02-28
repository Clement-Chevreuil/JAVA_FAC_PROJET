package com.example.application_entreprise_projet.DAO.SHOPPINGBAG;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBagDAOImpl implements IShoppingBagDAO{

    private String jdbcURL = "jdbc:mysql://localhost:3306/application_entreprise_projet";
    private String jdbcUsername = "root";
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ShoppingBagDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = "";
    }

    public ShoppingBagDAOImpl() {}

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
    public void add(ShoppingBag shoppingBag) throws SQLException {

        String sql = "INSERT INTO shoppingbag (idUser, idProduce) VALUES (?,?)";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, shoppingBag.getUser().getId());
        statement.setInt(2, shoppingBag.getProduce().getId());

        statement.executeUpdate();
        statement.close();
        disconnect();

    }

    @Override
    public void update(ShoppingBag shoppingBag) throws SQLException {}

    @Override
    public void delete(ShoppingBag shoppingBag) throws SQLException {

        String sql = "DELETE FROM shoppingbag where idUser = ? AND idProduce = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, shoppingBag.getUser().getId());
        statement.setInt(2, shoppingBag.getProduce().getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

    }
    @Override
    public void bagValidation(ShoppingBag s) throws SQLException {
        String sql1 = "SELECT MAX(noCommande) as no FROM shoppingbag";
        String sql2 = "UPDATE shoppingbag SET noCommande = ? , dateCommandeValidate = NOW(), dateClickAndCollect = ? WHERE idUser = ? AND noCommande IS NULL";
        String sql3 = "SELECT id FROM shoppingbag WHERE idUser <> ? AND idProduce IN (SELECT idProduce FROM shoppingbag WHERE noCommande = ?);";


        connect();

        int noCommande = 0;
        PreparedStatement statement1 = jdbcConnection.prepareStatement(sql1);
        ResultSet result = statement1.executeQuery();
        if (result.next()) {
            noCommande = result.getInt("no");
            noCommande = noCommande + 1;
        }
        if(noCommande == 0)
        {
            noCommande = 1;
        }
        statement1.close();

        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        statement2.setInt(1, noCommande);
        statement2.setString(2, s.getDateClickAndCollect());
        statement2.setInt(3, s.getUser().getId());

        statement2.executeUpdate();
        statement2.close();

        PreparedStatement statement3 = jdbcConnection.prepareStatement(sql3);
        statement3.setInt(2, noCommande);
        statement3.setInt(1, s.getUser().getId());
        ResultSet resulta = statement3.executeQuery();
        List<Integer> lisId = new ArrayList<>();
        while (resulta.next()) {

            int id = resulta.getInt("id");
            lisId.add(id);
        }
        statement3.close();


        if (lisId.size() != 0){

            String sql4 = "DELETE FROM shoppingbag";
            for(int i = 0; i < lisId.size(); i++)
            {

                if(i == 0){sql4 += " WHERE id = " + lisId.get(i);}
                else{sql4 += " OR id = " + lisId.get(i);}

            }
            sql4 += ";";

            System.out.println(sql4);
            PreparedStatement statement4 = jdbcConnection.prepareStatement(sql4);
            statement4.executeUpdate();
            statement4.close();
        }
        disconnect();

    }


    @Override
    public List<ShoppingBag> findByConsumer(User u) throws SQLException {

        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = "SELECT s.id, idUser, idProduce, name, category, price, expirationDate FROM shoppingbag s, produce p WHERE s.idUser = ? AND p.id = s.idProduce AND noCommande IS NULL;";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            int idUser = result.getInt("idUser");
            int idProduce = result.getInt("idProduce");

            String name = result.getString("name");
            String category = result.getString("category");
            Double price = result.getDouble("price");;
            String expirationDate = result.getString("expirationDate");

            User userBag = new User();

            Produce produceBag = new Produce();
            userBag.setId(idUser);
            produceBag.setId(idProduce);
            produceBag.setName(name);
            produceBag.setCategory(category);
            produceBag.setPrice(price);
            produceBag.setExpirationDate(expirationDate);

            ShoppingBag shoppingBag = new ShoppingBag(id, userBag, produceBag);
            shoppingBagList.add(shoppingBag);
        }
        statement.close();
        disconnect();

        return shoppingBagList;
    }

    @Override
    public List<ShoppingBag> findAll() throws SQLException {
        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = "SELECT * FROM shoppingbag";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setString(1, u.getEmail());

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            int idUser = result.getInt("idUser");
            int idProduce = result.getInt("idProduce");
            User userBag = new User();
            Produce produceBag = new Produce();
            userBag.setId(idUser);
            produceBag.setId(idProduce);
            ShoppingBag shoppingBag = new ShoppingBag(id, userBag, produceBag);
            shoppingBagList.add(shoppingBag);
        }

        statement.close();
        disconnect();

        return shoppingBagList;
    }

    @Override
    public List<ShoppingBag> findValidate(User u) throws SQLException {
        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = "SELECT s.id, idUser, idProduce, name, category, price, expirationDate, noCommande, dateCommandeValidate FROM shoppingbag s, produce p WHERE s.idUser = ? AND p.id = s.idProduce AND noCommande IS NOT NULL;";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            int idUser = result.getInt("idUser");
            int idProduce = result.getInt("idProduce");
            int noCommande = result.getInt("noCommande");

            String name = result.getString("name");
            String category = result.getString("category");
            Double price = result.getDouble("price");;
            String expirationDate = result.getString("expirationDate");
            String dateCommandeValidate = result.getString("dateCommandeValidate");

            User userBag = new User();

            Produce produceBag = new Produce();
            userBag.setId(idUser);
            produceBag.setId(idProduce);
            produceBag.setName(name);
            produceBag.setCategory(category);
            produceBag.setPrice(price);
            produceBag.setExpirationDate(expirationDate);

            ShoppingBag shoppingBag = new ShoppingBag(id, userBag, produceBag, noCommande, dateCommandeValidate);
            shoppingBagList.add(shoppingBag);
        }
        statement.close();
        disconnect();

        return shoppingBagList;
    }



    @Override
    public List<ShoppingBag> findCommandToTrader(User u) throws SQLException {

        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = " SELECT s.noCommande, s.dateCommandeValidate, u.country, u.city, u.firstName, u.lastName, u.postCode, u.street, u.id, u.email from shoppingbag s , produce p, user u where s.idProduce = p.id AND p.userId = ? AND p.userId = u.id GROUP BY s.noCommande;";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());
        ResultSet result = statement.executeQuery();

        while (result.next()) {


            int noCommande = result.getInt("noCommande");
            int idUser = result.getInt("id");
            String dateCommandeValidate = result.getString("dateCommandeValidate");
            String firstName = result.getString("firstName");
            String email = result.getString("email");
            String lastName = result.getString("lastName");
            String country = result.getString("country");
            String city = result.getString("city");
            String steet = result.getString("street");
            int postCode = result.getInt("postCode");
            User userBag = new User(idUser,firstName, lastName,email, country,city,postCode,steet);
            Produce produceBag = new Produce();


            ShoppingBag shoppingBag = new ShoppingBag(userBag, produceBag, noCommande, dateCommandeValidate);
            shoppingBagList.add(shoppingBag);
        }
        statement.close();
        disconnect();

        return shoppingBagList;
    }

    @Override
    public List<ShoppingBag> findCommandToConsumer(User u) throws SQLException {

        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = " SELECT s.noCommande, s.dateCommandeValidate from shoppingbag s where idUser = ? GROUP BY s.noCommande;";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, u.getId());
        ResultSet result = statement.executeQuery();

        while (result.next()) {


            int noCommande = result.getInt("noCommande");
            String dateCommandeValidate = result.getString("dateCommandeValidate");
            User userBag = new User();
            Produce produceBag = new Produce();


            ShoppingBag shoppingBag = new ShoppingBag(userBag, produceBag, noCommande, dateCommandeValidate);
            shoppingBagList.add(shoppingBag);
        }
        statement.close();
        disconnect();

        return shoppingBagList;
    }

    @Override
    public List<ShoppingBag> findCommandDetails(int noCommande) throws SQLException {

        System.out.println(noCommande);
        List<ShoppingBag> shoppingBagList = new ArrayList<>();
        String sql = " SELECT name, category, price, expirationDate, idUser, idProduce, dateCommandeValidate, firstName, lastName, email, country, city, postCode, street, dateClickAndCollect FROM shoppingbag s, produce p, user u WHERE s.idProduce = p.id AND s.idUser = u.id AND s.noCommande = ?;";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, noCommande);
        ResultSet result = statement.executeQuery();

        while (result.next()) {


            String name = result.getString("name");
            String category = result.getString("category");
            int price = result.getInt("price");
            String expirationDate = result.getString("expirationDate");
            int idUser = result.getInt("idUser");
            int idProduce = result.getInt("idProduce");
            String dateCommandeValidate = result.getString("dateCommandeValidate");
            String firstName = result.getString("firstName");
            String email = result.getString("email");
            String lastName = result.getString("lastName");
            String country = result.getString("country");
            String city = result.getString("city");
            String street = result.getString("street");
            int postCode = result.getInt("postCode");
            String dateClickAndCollect = result.getString("dateClickAndCollect");

            User userBag = new User(idUser, firstName, lastName, email, country,city,postCode, street);
            Produce produceBag = new Produce(idProduce, name, category, price, expirationDate);
            ShoppingBag shoppingBag = new ShoppingBag(userBag, produceBag, noCommande, dateCommandeValidate, dateClickAndCollect);

            shoppingBagList.add(shoppingBag);
        }
        statement.close();
        disconnect();

        return shoppingBagList;
    }

    @Override
    public int StatisticYear(String option1) throws SQLException {
        Integer statistic = 0;

        String sql;

        sql = "SELECT COUNT(id) as stat FROM shoppingbag WHERE YEAR(dateCommandeValidate) = ?;";
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
    public int StatisticMonth(int option1, String option2) throws SQLException {
        Integer statistic = 0;

        String sql;
        System.out.println(option1);
        System.out.println(option2);
        sql = "SELECT COUNT(id) as stat FROM shoppingbag WHERE MONTH (dateCommandeValidate) = ? AND YEAR(dateCommandeValidate) = ? ;";
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


    private void init() {
    }
}
