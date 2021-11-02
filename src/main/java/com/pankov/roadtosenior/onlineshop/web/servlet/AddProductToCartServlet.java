package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.security.Session;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import com.pankov.roadtosenior.onlineshop.util.CookieParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductToCartServlet extends HttpServlet {
    private final UserService userService;

    public AddProductToCartServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = CookieParser.getUserToken(req);

        long id = Long.parseLong(req.getParameter("id"));
        userService.addProductToCart(token, id);

        resp.sendRedirect("/");
    }
}
