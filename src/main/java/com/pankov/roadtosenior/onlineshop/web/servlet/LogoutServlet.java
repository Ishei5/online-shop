package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.util.CookieParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private final SecurityService securityService;

    public LogoutServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = CookieParser.getUserToken(request);
        if (token != null) {
            securityService.logout(token);
        }

        response.sendRedirect("/login");
    }
}
