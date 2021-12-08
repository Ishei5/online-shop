package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

import java.util.List;

public class CartService {
    private final SecurityService securityService = ServiceLocator.getService(SecurityService.class);
    private final ProductService productService = ServiceLocator.getService(ProductService.class);

    public CartService() {}

    public void addProductToCart(String token, long productId) {
        Product product = productService.getById(productId);
        securityService.getSession(token).getCart().add(product);
    }

    public void removeProductFromCart(String token, long productId) {
        List<Product> cart = securityService.getSession(token).getCart();
        cart.removeIf(product -> product.getId() == productId);
    }
}
