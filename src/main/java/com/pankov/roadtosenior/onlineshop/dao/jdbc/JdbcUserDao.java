package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import com.pankov.roadtosenior.JdbcTemplate;
import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper.UserRowMapper;
import com.pankov.roadtosenior.onlineshop.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcUserDao implements UserDao {
    private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private final static String FIND_USER_BY_NAME_QUERY =
            "SELECT id, username, password, salt, role_id FROM users WHERE username = ?;";

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_USER_BY_NAME_QUERY, USER_ROW_MAPPER, name);
    }
}