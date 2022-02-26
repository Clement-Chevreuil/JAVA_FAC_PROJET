package com.example.application_entreprise_projet.WEB;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.ShoppingBag;
import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.METIER.PRODUCE.IProduceMetier;
import com.example.application_entreprise_projet.METIER.SHOPPINGBAG.IShoppingBagMetier;
import com.example.application_entreprise_projet.METIER.USER.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class AllController extends HttpServlet {

    @Autowired
    private IUserMetier user;

    @Autowired
    private IProduceMetier produce;

    @Autowired
    private IShoppingBagMetier shoppingBag;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @RequestMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User u,Model model) throws SQLException {
        user.add(u);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/connexion")
    public String connexion(User u, HttpServletRequest request, HttpServletResponse response, Model model) throws SQLException, IOException {
        User userValidation = user.connexion(u);

        HttpSession session = request.getSession();

        if(userValidation != null)
        {
            session.setAttribute("user", userValidation);
            if(userValidation.getAdmin() == true)
            {
                model.addAttribute("traders",user.findAllTraders());
                model.addAttribute("consumers",user.findAllConsumers());
                return "admin";
            }

            else if(userValidation.getTrader() == true)
            {
                if(userValidation.getTraderValidation() == false)
                {
                    return "traderNotValidate";
                }
                else
                {
                    model.addAttribute("produces",produce.findAllByUserID(userValidation));
                    model.addAttribute("produce", new Produce());
                    model.addAttribute("commande",shoppingBag.findAllCommande(userValidation));
                    return "trader";
                }

            }
            else
            {
                model.addAttribute("produces",produce.findAllNotReserve(userValidation));
                return "consumer";
            }
        }
        else {return "erreur";}

    }

    @RequestMapping("/Deconnexion")
    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        ((HttpServletResponse) response).sendRedirect("index.jsp");

    }

    @RequestMapping("/ValidationTrader")
    public String ValidationTrader(int id,Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        user.validationTrader(id);
        model.addAttribute("traders",user.findAllTraders());
        model.addAttribute("consumers",user.findAllConsumers());
        return "admin";

    }

    @RequestMapping("/AdminIndex")
    public String AdminIndex(Model model) throws SQLException {
        model.addAttribute("user", new User());
        model.addAttribute("traders",user.findAllTraders());
        model.addAttribute("consumers",user.findAllConsumers());
        return "admin";
    }

    @RequestMapping("/TraderIndex")
    public String TraderIndex(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("user", new User());
        model.addAttribute("produces",produce.findAllByUserID(u));
        model.addAttribute("produce", new Produce());
        model.addAttribute("commande",shoppingBag.findAllCommande(u));
        return "trader";
    }

    @RequestMapping("/ConsumerIndex")
    public String ConsumerIndex(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("produces",produce.findAllNotReserve(u));
        return "consumer";
    }

    @RequestMapping("/editUser")
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        return "updateProfil";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User u, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User userToGetID = (User) session.getAttribute("user");
        u.setId(userToGetID.getId());
        u.setTrader(userToGetID.getTrader());
        u.setAdmin(userToGetID.getAdmin());
        u.setTraderValidation(userToGetID.getTraderValidation());
        user.update(u);
        session.setAttribute("user", u);
        return "index";
    }

    @RequestMapping("/deleteUser")
    public String delete( @RequestParam int id , Model model) throws SQLException {
        user.delete(id);
        model.addAttribute("user", new User());
        model.addAttribute("traders",user.findAllTraders());
        model.addAttribute("consumers",user.findAllConsumers());
        return "admin";
    }



    //Produce


    @RequestMapping("/saveProduce")
    public String saveProduce(Produce p, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        p.setUser(userSession);
        produce.add(p);
        model.addAttribute("produce", new Produce());
        model.addAttribute("produces",produce.findAllByUserID(userSession));
        model.addAttribute("commande",shoppingBag.findAllCommande(userSession));

        return "trader";
    }

    @RequestMapping("/deleteProduce")
    public String deleteProduce( @RequestParam int id , Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        produce.delete(id);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("user", new User());
        model.addAttribute("produces",produce.findAllByUserID(u));
        model.addAttribute("commande",shoppingBag.findAllCommande(u));
        model.addAttribute("produce", new Produce());
        return "trader";
    }

    @RequestMapping("/editProduce")
    public String editProduce( @RequestParam int id , Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Produce findProd = produce.find(id);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("produces",produce.findAllByUserID(u));
        model.addAttribute("produce", findProd);
        return "traderEdit";
    }

    @RequestMapping("/updateProduce")
    public String updateProduce(Produce p , Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        produce.update(p);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("produces",produce.findAllByUserID(u));
        model.addAttribute("commande",shoppingBag.findAllCommande(u));
        model.addAttribute("produce", new Produce());
        return "trader";
    }

    //Shopping Bag

    @RequestMapping("/addShoppingBag")
    public String addShoppingBag(int id, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        Produce produce1 = produce.find(id);
        ShoppingBag bag = new ShoppingBag(userSession, produce1);
        shoppingBag.addBag(bag);

        model.addAttribute("produces",produce.findAllNotReserve(userSession));

        return "consumer";
    }


    @RequestMapping("/shoppingBagIndex")
    public String shoppingBagIndex(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        model.addAttribute("user", new User());
        model.addAttribute("produces",produce.findAllByUserID(u));
        model.addAttribute("commande",shoppingBag.findAllCommande(u));
        model.addAttribute("produce", new Produce());

        return "trader";
    }

    @RequestMapping("/ShoppingBagWaiting")
    public String ShoppingBagWaiting(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<ShoppingBag> shopList = shoppingBag.getAllBagConsumer(u);
        model.addAttribute("produces", shopList );


        return "consumerCommandWaitingValidation";
    }

    @RequestMapping("/ShoppingBagValidate")
    public String ShoppingBagValidate(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<ShoppingBag> shopList = shoppingBag.getAllBagValidateConsumer(u);
        model.addAttribute("shoppingBag", shopList );


        return "shoppingBagValidate";
    }

    @RequestMapping("/ShoppingBagValidation")
    public String ShoppingBagValidation(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String dateClick = request.getParameter("dateClick");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        ShoppingBag shop = new ShoppingBag();
        shop.setUser(u);
        shop.setDateClickAndCollect(dateClick);

        shoppingBag.bagValidation(shop);
        List<ShoppingBag> shopList = shoppingBag.getAllBagConsumer(u);
        model.addAttribute("produces", shopList );


        return "consumerCommandWaitingValidation";
    }

    @RequestMapping("/traderCommandDetail")
    public String traderCommandDetail(@RequestParam int noCommande, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        List<ShoppingBag> commandDetails = shoppingBag.getDetailsCommande(noCommande);
        model.addAttribute("commandDetails", commandDetails);

        return "traderCommandDetails";
    }

    @RequestMapping("/consumerCommandList")
    public String consumerCommandList(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        List<ShoppingBag> commandDetails = shoppingBag.findAllCommandToConsumer(u);
        model.addAttribute("commandDetails", commandDetails);

        return "consumerCommandList";
    }

    @RequestMapping("/consumerCommand")
    public String consumerCommand(@RequestParam int noCommande, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        List<ShoppingBag> commandDetails = shoppingBag.getDetailsCommande(noCommande);
        model.addAttribute("commandDetails", commandDetails);



        return "consumerCommand";
    }

    @RequestMapping("/adminStatistic")
    public String adminStatistic(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");
        String option = request.getParameter("option");

        List<String> MONTH = new ArrayList<String>();
        MONTH.add("JANUARY");
        MONTH.add("FEBRUARY");
        MONTH.add("MARCH");
        MONTH.add("APRIL");
        MONTH.add("MAY");
        MONTH.add("JUN");
        MONTH.add("JULY");
        MONTH.add("AUGUST");
        MONTH.add("SEPTEMBER");
        MONTH.add("OCTOBER");
        MONTH.add("NOVEMBER");
        MONTH.add("DECEMBER");

        Date d = new Date();

        List<String> YEAR = new ArrayList<String>();
        for(int i = 4; i != -1 ; i--  )
        {
            int anneListe = Integer.valueOf(option) - i;
            YEAR.add(String.valueOf(anneListe));
        }

        List<String> select;
        List<Integer> statYEAR = new ArrayList<>();
        List<Integer> statMONTH = new ArrayList<>();

        for(int i = 0; i < YEAR.size(); i++)
        {
            int resultatRequete = user.StatisticYear(YEAR.get(i));
            statYEAR.add(resultatRequete);
        }

        for(int i = 0; i < MONTH.size(); i++)
        {
            int resultatRequete = user.StatisticMonth(i+1, option);
            statMONTH.add(resultatRequete);
        }

        session.setAttribute("option", option);
        session.setAttribute("YEAR", YEAR);
        session.setAttribute("MONTH", MONTH);
        session.setAttribute("listStatYEAR", statYEAR);
        session.setAttribute("listStatMONTH", statMONTH);
        session.setAttribute("option", Integer.valueOf(option));



        return "adminStatistic";
    }







    //SELECT p.id, name, category, price, expirationDate, sold, u.email, u.city FROM produce p, shoppingbag s, user u WHERE p.userId = ? AND p.id = s.idProduce AND s.idUser = u.id AND sold = 1 AND p.id = ?;
    //SELECT s.noCommande from shoppingbag s , produce p where s.idProduce = p.id AND p.userId = 5 GROUP BY s.noCommande;

}
