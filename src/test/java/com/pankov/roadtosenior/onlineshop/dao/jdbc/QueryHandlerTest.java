package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryHandlerTest {

    @Test
    public void testHandleQuery() {
        String selectByIdQuery = "SELECT id, name, price, creationDate, description FROM products WHERE id = ?;";
        String expectedSelectByIdQuery = "SELECT id, name, price, creationDate, description " +
                "FROM products WHERE id = '1';";

        String selectByDescQuery = "SELECT id, name, price, creationDate, description FROM products " +
                "WHERE description LIKE ?;";
        String expectedSelectByDescQuery = "SELECT id, name, price, creationDate, description FROM products " +
                "WHERE description LIKE 'product\";DELETE ALL FROM products';";


        String actualSelectByIdQuery = QueryHandler.handleQuery(selectByIdQuery,1);
        String actualSelectByDescQuery = QueryHandler.handleQuery(selectByDescQuery,"product';DELETE ALL FROM products");


        assertEquals(expectedSelectByIdQuery, actualSelectByIdQuery);
        assertEquals(expectedSelectByDescQuery, actualSelectByDescQuery);
    }

    @Test
    @DisplayName("Test should throw RuntimeException when quantity of parameters in query and provided params are not equals")
    public void testHandleQueryWithException() {

        assertThrows(RuntimeException.class, () -> {
            String selectByIdQuery = "SELECT id, name, price, creationDate, description FROM products WHERE id = ?;";
            QueryHandler.handleQuery(selectByIdQuery, 1, "extra parameter");
        });
    }
}