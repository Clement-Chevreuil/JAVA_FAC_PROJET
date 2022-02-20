package com.example.application_entreprise_projet.WEB;

import com.example.application_entreprise_projet.CLASS.User;
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

@Controller
public class UserController extends HttpServlet {

    @Autowired
    private IUserMetier user;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @RequestMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/connexion")
    public String connexion(User u, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User userValidation = user.connexion(u);

        HttpSession session = request.getSession();

        if(userValidation != null)
        {
            System.out.println(userValidation.getEmail());
            session.setAttribute("user", userValidation);

            if(userValidation.getAdmin() == true) {return "admin";}
            else if(userValidation.getTrader() == true) {return "trader";}
            else {return "consumer";}
        }
        else {return "erreur";}

    }

    @RequestMapping("/Deconnexion")
    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        ((HttpServletResponse) response).sendRedirect("index.jsp");

    }

    @RequestMapping("/AdminIndex")
    public String AdminIndex(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "admin";
    }

    @RequestMapping("/TraderIndex")
    public String TraderIndex(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "trader";
    }

    @RequestMapping("/ConsumerIndex")
    public String ConsumerIndex(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "consumer";
    }

    @RequestMapping("/editUser")
    public String edit(@RequestParam int id , Model model)
    {

        model.addAttribute("user", user.find(id));
        model.addAttribute("users",user.findAll());
        return "admin";
    }
    @RequestMapping("/deleteUser")
    public String delete( @RequestParam int id , Model model)
    {
        user.delete(user.find(id));
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "admin";
    }


}
