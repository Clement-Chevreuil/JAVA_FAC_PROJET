package com.example.application_entreprise_projet.FILTER;

import com.example.application_entreprise_projet.CLASS.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login", "/register", "/saveUser"})
public class FilterIsLog implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user= (User)session.getAttribute("user");

        if (session.getAttribute("user") != null ) {

            System.out.println("error redirection ot the good page");

            if(user.getTrader() == true)
            {
                ((HttpServletResponse) response).sendRedirect("TraderIndex");
            }
            else if(user.getAdmin() == true)
            {
                ((HttpServletResponse) response).sendRedirect("AdminIndex");
            }
            else
            {
                ((HttpServletResponse) response).sendRedirect("ConsumerIndex");
            }
        }
        else
        {
            chain.doFilter(request, response);
        }

    }
}
