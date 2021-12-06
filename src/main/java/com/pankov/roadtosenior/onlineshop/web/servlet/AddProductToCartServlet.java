package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.service.CartService;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import com.pankov.roadtosenior.onlineshop.web.util.CookieParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductToCartServlet extends HttpServlet {
    private final CartService cartService;

    public AddProductToCartServlet(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = CookieParser.getUserToken(req);

        long id = Long.parseLong(req.getParameter("id"));
        cartService.addProductToCart(token, id);

        resp.sendRedirect("/");
    }
}
