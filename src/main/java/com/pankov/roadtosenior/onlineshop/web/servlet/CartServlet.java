package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.security.Session;
import com.pankov.roadtosenior.onlineshop.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> parameterMap = new HashMap<>();
        List<Product> productList = null;
        Session session = (Session) req.getAttribute("session");

        if (session != null) {
            productList = session.getCart();
        }
        parameterMap.put("products", productList);
        String page = pageGenerator.getPage("cart.ftl", parameterMap);

        resp.getWriter().println(page);
    }
}
