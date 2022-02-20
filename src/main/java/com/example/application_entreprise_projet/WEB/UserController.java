package com.example.application_entreprise_projet.WEB;

import com.example.application_entreprise_projet.CLASS.User;
import com.example.application_entreprise_projet.METIER.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

@Controller
public class UserController extends HttpServlet {

    @Autowired
    private IUserMetier user;

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

    @RequestMapping("/index")
    public String index(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "admin";
    }
    @RequestMapping("/saveUser")
    public String save(  User p , Model model) throws SQLException {
        user.add(p);
        model.addAttribute("user", new User());
        model.addAttribute("users",user.findAll());
        return "admin";
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
