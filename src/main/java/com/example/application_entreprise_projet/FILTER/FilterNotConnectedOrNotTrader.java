package com.example.application_entreprise_projet.FILTER;

import com.example.application_entreprise_projet.CLASS.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/TraderIndex","/saveProduce","/editProduce","/deleteProduce","/shoppingBagIndex","/traderCommandDetail" })
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
                System.out.println("error redirection ot the good page");
                if(user.getTrader() == false)
                {
                    ((HttpServletResponse) response).sendRedirect("ConsummerIndex");
                }
                else if(user.getAdmin() == true)
                {
                    ((HttpServletResponse) response).sendRedirect("AdminIndex");
                }
                else
                {
                    ((HttpServletResponse) response).sendRedirect("index.jsp");
                }
            }
            else
            {
                if(user.getTraderValidation() == false)
                {
                    ((HttpServletResponse) response).sendRedirect("traderNotValidate.jsp");
                }
                else
                {
                    chain.doFilter(request, response);
                }

            }

        }
    }
}
