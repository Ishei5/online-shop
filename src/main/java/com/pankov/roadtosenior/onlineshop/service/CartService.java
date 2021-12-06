package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

import java.util.List;

public class CartService {
    private final SecurityService securityService;
    private final ProductService productService;

    public CartService(SecurityService securityService, ProductService productService) {
        this.securityService = securityService;
        this.productService = productService;
    }

    public void addProductToCart(String token, long productId) {
        Product product = productService.getById(productId);
        securityService.getSession(token).getCart().add(product);
    }

    public void removeProductFromCart(String token, long productId) {
        List<Product> cart = securityService.getSession(token).getCart();
        cart.removeIf(product -> product.getId() == productId);
    }
}
