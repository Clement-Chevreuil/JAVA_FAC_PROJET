package com.example.application_entreprise_projet.WEB;

import com.example.application_entreprise_projet.CLASS.Produce;
import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.METIER.IProduceMetier;
import com.example.application_entreprise_projet.METIER.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@org.springframework.stereotype.Controller
public class AllController extends HttpServlet {

    @Autowired
    private IUserMetier user;

    @Autowired
    private IProduceMetier produce;

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
                    return "TraderNotValidate";
                }
                else
                {
                    model.addAttribute("produces",produce.findAllByUserID(userValidation));
                    model.addAttribute("produce", new Produce());
                    return "trader";
                }

            }
            else
            {
                model.addAttribute("produces",produce.findAll());
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
        return "trader";
    }

    @RequestMapping("/ConsumerIndex")
    public String ConsumerIndex(Model model) throws SQLException {
        model.addAttribute("user", new User());
        model.addAttribute("produces",produce.findAll());
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
        user.update(u);
        return "index";
    }

    @RequestMapping("/deleteUser")
    public String delete( @RequestParam int id , Model model) throws SQLException {
        user.delete(user.find(id));
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
        return "trader";
    }






}
