package com.example.application_entreprise_projet.FILTER;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login", "/register"})
public class FilterIsLog implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session.getAttribute("user") != null ) {
            System.out.println("session exist");
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
        else
        {
            chain.doFilter(request, response);
        }

    }
}
