package com.pankov.roadtosenior.onlineshop.dao;

import com.pankov.roadtosenior.onlineshop.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(Long id);

    void delete(Long id);

    void update(Product product);

    Long add(Product product);

    List<Product> findByMatchInDescription(String searchText);
}
