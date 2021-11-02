package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcProductDaoITest {

    @Test
    @Disabled
    public void testGetAll() {
        ConnectionFactory connectionFactory = null;
        JdbcProductDao jdbcProductDao = new JdbcProductDao(connectionFactory);
        List<Product> productList = jdbcProductDao.findAll();

        assertFalse(productList.isEmpty());
        for (Product product : productList) {
            assertNotNull(product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getPrice());
            assertNotNull(product.getCreationDate());
        }
    }
}