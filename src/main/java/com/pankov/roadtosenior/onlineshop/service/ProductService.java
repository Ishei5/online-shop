package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductDao productDao;

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public Product getById(Long id) {
        return productDao.findById(id);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    public Product add(Product product) {
       return productDao.add(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public List<Product> findByMatchInDescription(String searchText) {
        return productDao.findByMatchInDescription(searchText);
    }
}
