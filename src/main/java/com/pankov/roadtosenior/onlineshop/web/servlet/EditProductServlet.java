package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import com.pankov.roadtosenior.onlineshop.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class EditProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = new PageGenerator();
    private ProductService productService;

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Map<String, Object> productParameters = Map.of("product", productService.getById(id));

        String page = pageGenerator.getPage("editProduct.ftl", productParameters);

        resp.getWriter().println(page);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        LocalDateTime date = LocalDateTime.parse(req.getParameter("creationDate"));
        String description = req.getParameter("description");
        productService.update(Product
                .builder()
                .id(id)
                .name(name)
                .price(price)
                .creationDate(date)
                .description(description)
                .build());

        resp.sendRedirect("/");
    }
}
