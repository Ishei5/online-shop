package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import com.pankov.roadtosenior.JdbcTemplate;
import com.pankov.roadtosenior.holder.GeneratedKeyHolder;
import com.pankov.roadtosenior.holder.KeyHolder;
import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.pankov.roadtosenior.onlineshop.entity.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class JdbcProductDao implements ProductDao {

    private final static ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private final static String FIND_ALL_QUERY = "SELECT id, name, price, creationDate, description FROM products;";
    private final static String FIND_BY_ID_QUERY = "SELECT id, name, price, creationDate, description FROM products WHERE id = ?;";
    private final static String DELETE_BY_ID_QUERY = "DELETE FROM products WHERE id = ?;";
    private final static String ADD_QUERY = "INSERT INTO products (name, price, creationDate, description) VALUES (?, ?, ?, ?);";
    private final static String UPDATE_QUERY = "UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?;";
    private final static String FIND_ALL_BY_MATCH_IN_DESCRIPTION =
            "SELECT id, name, price, creationDate, description FROM products WHERE description LIKE ?;";

    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, PRODUCT_ROW_MAPPER);
    }

    @Override
    public Product findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, PRODUCT_ROW_MAPPER, id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID_QUERY, id);
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(UPDATE_QUERY,
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getId());
    }

    @SneakyThrows
    @Override
    public Product add(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(ADD_QUERY, new String[]{"id"});
                    statement.setString(1, product.getName());
                    statement.setDouble(2, product.getPrice());
                    statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    statement.setString(4, product.getDescription());

                    return statement;
                },
                keyHolder);

        Product addedProduct = findById(keyHolder.getKey().longValue());
        log.info("Created product - {}", addedProduct.toString());

        return addedProduct;
    }

    @Override
    public List<Product> findByMatchInDescription(String searchText) {
        return jdbcTemplate.query(FIND_ALL_BY_MATCH_IN_DESCRIPTION, PRODUCT_ROW_MAPPER, searchText);
    }
}
