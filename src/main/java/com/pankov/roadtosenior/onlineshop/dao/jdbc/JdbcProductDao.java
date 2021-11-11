package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.pankov.roadtosenior.onlineshop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private final static ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private final static String FIND_ALL_QUERY = "SELECT id, name, price, creationDate, description FROM products;";
    private final static String FIND_BY_ID_QUERY = "SELECT id, name, price, creationDate, description FROM products WHERE id = ?;";
    private final static String DELETE_BY_ID_QUERY = "DELETE FROM products WHERE id = ?;";
    private final static String ADD_QUERY = "INSERT INTO products (name, price, creationDate, description) VALUES (?, ?, ?, ?);";
    private final static String UPDATE_QUERY = "UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?;";
    private final static String FIND_ALL_BY_MATCH_IN_DESCRIPTION =
            "SELECT id, name, price, creationDate, description FROM products WHERE description LIKE ?;";

    private DataSource dataSource;

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY)) {

            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                productsList.add(product);
            }

            return productsList;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get products from DB", exception);
        }
    }

    @Override
    public Product findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryHandler.handleQuery(FIND_BY_ID_QUERY, id))) {

            if (!resultSet.next()) {
                throw new SQLException("Product with ID = " + id + " not found in DB");
            }

            return PRODUCT_ROW_MAPPER.mapRow(resultSet);

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get product from DB", exception);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(QueryHandler.handleQuery(DELETE_BY_ID_QUERY, id));

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot delete product from DB", exception);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(
                    QueryHandler.handleQuery(UPDATE_QUERY,
                            product.getName(),
                            product.getPrice(),
                            product.getDescription(),
                            product.getId()));

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot update product with ID " + product.getId(), exception);
        }
    }

    @Override
    public Long add(Product product) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(QueryHandler.handleQuery(
                    ADD_QUERY,
                    product.getName(),
                    product.getPrice(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    product.getDescription()),
                    Statement.RETURN_GENERATED_KEYS);

            int id = 0;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
            return (long) id;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot add product to DB", exception);
        }
    }

    @Override
    public List<Product> findByMatchInDescription(String searchText) {
        List<Product> productsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     QueryHandler.handleQuery(FIND_ALL_BY_MATCH_IN_DESCRIPTION, ("%" + searchText + "%")))) {

            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                productsList.add(product);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get products from DB", exception);
        }
        return productsList;
    }
}
