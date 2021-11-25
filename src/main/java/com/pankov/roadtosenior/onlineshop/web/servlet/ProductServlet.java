package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import com.pankov.roadtosenior.onlineshop.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.getInstance();
    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> productParameters = new HashMap<>();
        List<Product> productsList;

        String searchText = req.getParameter("search");

        if (searchText != null) {
            productsList = productService.findByMatchInDescription(searchText);
        } else {
            productsList = productService.getAll();
        }
        productParameters.put("products", productsList);

        String page = pageGenerator.getPage("product.ftl", productParameters);

        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect("/");
    }

}
