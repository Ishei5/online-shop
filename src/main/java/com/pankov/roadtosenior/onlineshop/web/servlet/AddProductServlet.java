package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import com.pankov.roadtosenior.onlineshop.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.getInstance();
    private ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = pageGenerator.getPage("addProduct.ftl", null);

        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        String description = req.getParameter("description");

        productService.add(Product
                .builder()
                .name(name)
                .price(price)
                .description(description)
                .build());

        resp.sendRedirect("/");
    }
}
