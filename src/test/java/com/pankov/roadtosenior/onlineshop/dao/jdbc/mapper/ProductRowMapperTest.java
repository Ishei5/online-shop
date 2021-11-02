package com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {
    @Test
    public void test() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("name")).thenReturn("Book \"Java 8 Lambdas\"");
        when(mockResultSet.getDouble("price")).thenReturn(999.9);

        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.SEPTEMBER, 21, 9, 30, 15);
        when(mockResultSet.getTimestamp("creationDate")).thenReturn(Timestamp.valueOf(localDateTime));

        Product actualProduct = productRowMapper.mapRow(mockResultSet);

        assertNotNull(actualProduct);
        assertEquals(1L, actualProduct.getId());
        assertEquals("Book \"Java 8 Lambdas\"", actualProduct.getName());
        assertEquals(999.9, actualProduct.getPrice());
        assertEquals(localDateTime, actualProduct.getCreationDate());
    }
}