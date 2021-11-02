package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.entity.Product;

import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public Product getById(Long id) {
        return productDao.findById(id);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    public void add(Product product) {
        productDao.add(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public List<Product> findByMatchInDescription(String searchText) {
        return productDao.findByMatchInDescription(searchText);
    }

    public void setJdbcProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
