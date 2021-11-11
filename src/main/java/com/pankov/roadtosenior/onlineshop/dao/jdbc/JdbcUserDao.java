package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.mapper.UserRowMapper;
import com.pankov.roadtosenior.onlineshop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUserDao implements UserDao {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private final static String FIND_USER_BY_NAME_QUERY =
            "SELECT id, username, password, salt, role_id FROM users WHERE username = ?;";

    private DataSource dataSource;

    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryHandler.handleQuery(FIND_USER_BY_NAME_QUERY, name))) {

            if (!resultSet.next()) {
                throw new SQLException("User with name = " + name + " not found in DB");
            }

            return USER_ROW_MAPPER.mapRow(resultSet);
        } catch (SQLException exception) {
            log.warn("Cannot can get user from DB");
            throw new RuntimeException("Cannot can get user from DB", exception);
        }
    }
}
