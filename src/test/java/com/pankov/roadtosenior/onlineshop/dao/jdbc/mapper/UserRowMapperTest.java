package com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper;

import com.pankov.roadtosenior.onlineshop.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("username")).thenReturn("user");
        when(mockResultSet.getString("password")).thenReturn("user");
        when(mockResultSet.getInt("role_id")).thenReturn(1);

        User user = userRowMapper.mapRow(mockResultSet);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("user", user.getUsername());
        assertEquals("user", user.getPassword());
        assertEquals(1, user.getId());
    }
}