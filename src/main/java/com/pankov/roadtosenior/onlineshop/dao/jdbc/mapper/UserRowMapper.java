package com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper;

import com.pankov.roadtosenior.mapper.RowMapper;
import com.pankov.roadtosenior.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String salt = resultSet.getString("salt");
        int roleId = resultSet.getInt("role_id");
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .salt(salt)
                .roleId(roleId)
                .build();
    }
}