package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.service.CartService;
import com.pankov.roadtosenior.onlineshop.service.ServiceLocator;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import com.pankov.roadtosenior.onlineshop.web.util.CookieParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProductFromCartServlet extends HttpServlet {

    private final CartService cartService = ServiceLocator.getService(CartService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = CookieParser.getUserToken(request);
        long id = Long.parseLong(request.getParameter("id"));
        cartService.removeProductFromCart(token, id);

        response.sendRedirect("/");
    }
}
