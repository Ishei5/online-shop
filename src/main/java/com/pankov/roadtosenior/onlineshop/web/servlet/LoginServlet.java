package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.web.PageGenerator;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

public class LoginServlet extends HttpServlet {
    private PageGenerator pageGenerator = new PageGenerator();
    private SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = pageGenerator.getPage("login.ftl", null);

        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String token = securityService.login(username, password);

        if (token == null) {
            resp.sendError(SC_UNAUTHORIZED);
            return;
        }
        Cookie cookie = new Cookie("user-token", token);
//            cookie.setMaxAge();
        resp.addCookie(cookie);
        resp.sendRedirect("/");

    }
}
