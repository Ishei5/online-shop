package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.service.UserService;
import com.pankov.roadtosenior.onlineshop.util.CookieParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProductFromCartServlet extends HttpServlet {

    private final UserService userService;

    public RemoveProductFromCartServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = CookieParser.getUserToken(request);
        long id = Long.parseLong(request.getParameter("id"));
        userService.removeProductFromCart(token, id);

        response.sendRedirect("/");
    }
}
