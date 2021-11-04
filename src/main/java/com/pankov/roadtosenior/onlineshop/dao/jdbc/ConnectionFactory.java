package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory implements DataSource {
    private String url;
    private String username;
    private String password;

//    Connection connection;

    @SneakyThrows
    public ConnectionFactory(Properties properties) {
        this.url = properties.getProperty("db.url") + "/onlineshop";
        this.username = properties.getProperty("db.username");
        this.password = properties.getProperty("db.password");

//        connection = DriverManager.getConnection(uri, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

/*    static class CachedConnection implements Connection {

    }*/
}
