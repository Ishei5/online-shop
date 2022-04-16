package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.security.Session;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class CartService {

    private final SecurityService securityService;
    private final ProductService productService;

    public void addProductToCart(String token, long productId) {
        Product product = productService.getById(productId);
        Session session = securityService.getSession(token);

        session.getCart().add(product);
        log.info(session.getCart().toString());
    }

    public void removeProductFromCart(String token, long productId) {
        List<Product> cart = securityService.getSession(token).getCart();
        cart.removeIf(product -> product.getId() == productId);
    }
}
