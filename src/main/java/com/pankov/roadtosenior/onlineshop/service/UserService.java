package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.entity.User;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

import java.util.Iterator;
import java.util.List;

public class UserService {
    private UserDao userDao;
    private SecurityService securityService;
    private ProductService productService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public User findByName(String name) {
        return userDao.findByName(name);
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
