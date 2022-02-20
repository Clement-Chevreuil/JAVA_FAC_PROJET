package com.example.application_entreprise_projet.FILTER;

import com.example.application_entreprise_projet.CLASS.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/TraderIndex"})
public class FilterNotConnectedOrNotTrader implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter execution");
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session.getAttribute("user") == null) {
            System.out.println("session null");
            //((HttpServletRequest) request).getRequestDispatcher("index.jsp").include(request, response);
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
        else
        {
            User user = (User) session.getAttribute("user");
            if(user.getAdmin() == true || user.getTrader() == false)
            {
                System.out.println("error");
                ((HttpServletResponse) response).sendRedirect("index.jsp");
            }
            else
            {
                chain.doFilter(request, response);
            }

        }
    }
}
