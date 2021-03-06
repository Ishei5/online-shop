package com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper;

import com.pankov.roadtosenior.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductRowMapper {

    public Product mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");

        Timestamp creationDateTimestamp = resultSet.getTimestamp("creationDate");
        LocalDateTime creationDate = creationDateTimestamp.toLocalDateTime();

        String description = resultSet.getString("description");

        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .creationDate(creationDate)
                .description(description == null ? "" : description)
                .build();
    }
}
